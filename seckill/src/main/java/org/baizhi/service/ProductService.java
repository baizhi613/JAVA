package org.baizhi.service;

import org.baizhi.model.SeckillProduct;

import java.util.List;

public interface ProductService {
    // 查询所有秒杀商品
    List<SeckillProduct> getAllProducts();

    SeckillProduct getProductById(Long productId);

    void addProduct(SeckillProduct product);

    void deleteProduct(Long productId);
}
