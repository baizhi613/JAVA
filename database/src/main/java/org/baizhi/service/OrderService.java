package org.baizhi.service;

import org.baizhi.model.Order;
import org.baizhi.model.OrderDetail;
import java.util.List;

public interface OrderService {
    Order getOrderById(Integer orderId);
    List<Order> getAllOrders();
    int createOrder(Order order);
    int updateOrder(Order order);
    int deleteOrder(Integer orderId);
    // 添加创建订单和订单详情的方法
    int createOrderWithDetails(Order order, List<OrderDetail> orderDetails);
} 