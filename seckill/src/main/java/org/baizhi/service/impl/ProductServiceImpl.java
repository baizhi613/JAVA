package org.baizhi.service.impl;

import jakarta.annotation.Resource;
import org.baizhi.mapper.OrderMapper;
import org.baizhi.mapper.ProductMapper;
import org.baizhi.model.SeckillProduct;
import org.baizhi.model.SeckillOrder;
import org.baizhi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<SeckillProduct> getAllProducts() {
        return productMapper.getAllProducts();
    }

    @Override
    public SeckillProduct getProductById(Long productId) {
        return productMapper.getProductById(productId);
    }

    @Override
    public void addProduct(SeckillProduct product) {
        productMapper.insertProduct(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productMapper.deleteProduct(productId);
    }

}
