package org.baizhi.service;

import org.baizhi.model.SeckillOrder;

import java.util.List;

public interface OrderService {
    // 秒杀下单
    void placeOrder(Long userId, Long productId) throws Exception;

    List<SeckillOrder> getAllOrders();

    void deleteAllOrders();
}
