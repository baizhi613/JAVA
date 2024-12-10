package org.baizhi.service.impl;

import org.baizhi.mapper.OrderMapper;
import org.baizhi.model.SeckillOrder;
import org.baizhi.model.SeckillProduct;
import org.baizhi.mapper.ProductMapper;
import org.baizhi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, ProductMapper productMapper) {
        this.orderMapper = orderMapper;
        this.productMapper = productMapper;
    }
    @Override
    public void placeOrder(Long userId, Long productId) throws Exception{
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

        // 判断秒杀商品库存是否充足
        if (product.getStock() <= 0) {
            throw new Exception("商品库存不足！");
        }

        // 防止重复秒杀，检查用户是否已经购买过该商品
        SeckillOrder existingOrder = orderMapper.getOrderByUserAndProduct(userId, productId);
        if (existingOrder != null) {
            throw new Exception("用户已秒杀该商品！");
        }

        // 创建秒杀订单
        SeckillOrder order = new SeckillOrder();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setOrderStatus(0); // 订单状态为未支付
        orderMapper.insertOrder(order);

        // 扣减库存
        productMapper.decreaseStock(productId);
    }

    @Override
    public List<SeckillOrder> getAllOrders() {
        return orderMapper.getAllOrders();
    }
}
