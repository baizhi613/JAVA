package org.baizhi.service.serviceImpl;

import org.baizhi.mapper.OrderMapper;
import org.baizhi.mapper.OrderDetailMapper;
import org.baizhi.model.Order;
import org.baizhi.model.OrderDetail;
import org.baizhi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Order getOrderById(Integer orderId) {
        return orderMapper.getOrderById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public int createOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    @Override
    public int deleteOrder(Integer orderId) {
        return orderMapper.deleteOrder(orderId);
    }

    @Override
    @Transactional
    public int createOrderWithDetails(Order order, List<OrderDetail> orderDetails) {
        int result = orderMapper.insertOrder(order);
        if (result > 0) {
            for (OrderDetail detail : orderDetails) {
                detail.setOrderId(order.getOrderId());
                orderDetailMapper.insertOrderDetail(detail);
            }
        }
        return result;
    }
} 