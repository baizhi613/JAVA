package org.baizhi.service.impl;

import org.baizhi.mapper.OrderMapper;
import org.baizhi.model.SeckillOrder;
import org.baizhi.model.SeckillProduct;
import org.baizhi.mapper.ProductMapper;
import org.baizhi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final RedisTemplate<String, String> redisTemplate;
// Lua脚本，用于释放分布式锁
// 定义一个私有的静态常量字符串，表示一个用于释放锁的Lua脚本
    private static final String RELEASE_LOCK_SCRIPT =
            // Lua脚本的开始部分，检查Redis中指定键的值是否与传入的参数值相等
            "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                    // 如果相等，说明当前线程持有锁，执行删除该键的操作（即释放锁）
                    "   return redis.call('del', KEYS[1]) " +
                    // 如果不相等，说明当前线程不持有锁
                    "else " +
                    // 返回0，表示释放锁操作失败
                    "   return 0 " +
                    "end";

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, ProductMapper productMapper, RedisTemplate<String, String> redisTemplate) {
        this.orderMapper = orderMapper;
        this.productMapper = productMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(Long userId, Long productId) throws Exception {
        // 定义分布式锁的key和value(value用于防止误删)
        String lockKey = "seckill:lock:" + productId;
        String lockValue = UUID.randomUUID().toString();
        
        try {
            // 尝试获取分布式锁，设置超时时间为30秒
            Boolean acquired = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 30, TimeUnit.SECONDS);
            if (acquired == null || !acquired) {
                throw new Exception("系统繁忙，请稍后再试！");
            }

            // 先查询库存，使用Redis缓存
            String stockKey = "seckill:stock:" + productId;
            Integer stock = null;
            String cachedStock = redisTemplate.opsForValue().get(stockKey);
            
            if (cachedStock != null) {
                stock = Integer.parseInt(cachedStock);
            } else {
                // 缓存未命中，查询数据库
                SeckillProduct product = productMapper.getProductById(productId);
                if (product == null) {
                    throw new Exception("商品不存在！");
                }
                stock = product.getStock();
                // 将库存放入缓存
                redisTemplate.opsForValue().set(stockKey, String.valueOf(stock), 1, TimeUnit.HOURS);
            }

            // 判断库存是否充足
            if (stock <= 0) {
                throw new Exception("商品库存不足！");
            }

            // 判断秒杀商品是否存在
            SeckillProduct product = productMapper.getProductById(productId);
            if (product == null) {
                throw new Exception("商品不存在！");
            }

            // 判断秒杀商品是否在有效时间内
            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(product.getStartTime()) || now.isAfter(product.getEndTime())) {
                throw new Exception("秒杀活动不在有效时间内！");
            }

            // 防止重复秒杀，使用Redis Set结构检查
            String userOrderKey = "seckill:user:orders:" + userId;
            Long isFirstTime = redisTemplate.opsForSet().add(userOrderKey, productId.toString());
            if (Boolean.FALSE.equals(isFirstTime)) {
                throw new Exception("用户已秒杀该商品！");
            }

            // 创建秒杀订单
            SeckillOrder order = new SeckillOrder();
            order.setUserId(userId);
            order.setProductId(productId);
            order.setOrderStatus(0); // 订单状态为未支付
            orderMapper.insertOrder(order);

            // 扣减库存
            int result = productMapper.decreaseStock(productId);
            if (result == 0) {
                throw new Exception("商品库存不足！");
            }

            // 更新Redis中的库存
            redisTemplate.opsForValue().decrement(stockKey);
            
        } finally {
            // 使用Lua脚本释放分布式锁，确保原子性和只释放自己的锁
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptText(RELEASE_LOCK_SCRIPT);
            redisScript.setResultType(Long.class);
            redisTemplate.execute(redisScript, Collections.singletonList(lockKey), lockValue);
        }
    }

    @Override
    public List<SeckillOrder> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public void deleteAllOrders() {
        orderMapper.deleteAllOrders();
    }
}
