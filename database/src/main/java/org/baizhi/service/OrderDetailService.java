package org.baizhi.service;

import org.baizhi.model.OrderDetail;
import java.util.List;

public interface OrderDetailService {
    OrderDetail getOrderDetailById(Integer orderDetailId);
    List<OrderDetail> getOrderDetailsByOrderId(Integer orderId);
    int createOrderDetail(OrderDetail orderDetail);
    int updateOrderDetail(OrderDetail orderDetail);
    int deleteOrderDetail(Integer orderDetailId);
} 