package org.baizhi.service.serviceImpl;

import org.baizhi.mapper.OrderDetailMapper;
import org.baizhi.model.OrderDetail;
import org.baizhi.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetail getOrderDetailById(Integer orderDetailId) {
        return orderDetailMapper.getOrderDetailById(orderDetailId);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(Integer orderId) {
        return orderDetailMapper.getOrderDetailsByOrderId(orderId);
    }

    @Override
    public int createOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.insertOrderDetail(orderDetail);
    }

    @Override
    public int updateOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.updateOrderDetail(orderDetail);
    }

    @Override
    public int deleteOrderDetail(Integer orderDetailId) {
        return orderDetailMapper.deleteOrderDetail(orderDetailId);
    }
} 