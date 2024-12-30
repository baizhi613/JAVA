package org.baizhi.controller;

import org.baizhi.model.OrderDetail;
import org.baizhi.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/{id}")
    public OrderDetail getOrderDetailById(@PathVariable("id") Integer id) {
        OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);
        if (orderDetail != null) {
            return orderDetail;
        } else {
            throw new RuntimeException("订单详情不存在！");
        }
    }

    @GetMapping("/order/{orderId}")
    public List<OrderDetail> getOrderDetailsByOrderId(@PathVariable("orderId") Integer orderId) {
        return orderDetailService.getOrderDetailsByOrderId(orderId);
    }

    @PostMapping
    public String createOrderDetail(@RequestBody OrderDetail orderDetail) {
        int result = orderDetailService.createOrderDetail(orderDetail);
        return result > 0 ? "订单详情创建成功！" : "订单详情创建失败！";
    }

    @PutMapping("/{id}")
    public String updateOrderDetail(@PathVariable("id") Integer id, @RequestBody OrderDetail orderDetail) {
        orderDetail.setOrderDetailId(id);
        int result = orderDetailService.updateOrderDetail(orderDetail);
        return result > 0 ? "订单详情更新成功！" : "订单详情更新失败！";
    }

    @DeleteMapping("/{id}")
    public String deleteOrderDetail(@PathVariable("id") Integer id) {
        int result = orderDetailService.deleteOrderDetail(id);
        return result > 0 ? "订单详情删除成功！" : "订单详情删除失败！";
    }
} 