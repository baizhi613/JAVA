package org.baizhi.controller;

import org.baizhi.model.Order;
import org.baizhi.model.OrderDetail;
import org.baizhi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Integer id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return order;
        } else {
            throw new RuntimeException("订单不存在！");
        }
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public String createOrder(@RequestBody Order order) {
        int result = orderService.createOrder(order);
        return result > 0 ? "订单创建成功！" : "订单创建失败！";
    }

    @PostMapping("/with-details")
    public String createOrderWithDetails(@RequestBody OrderRequest request) {
        int result = orderService.createOrderWithDetails(request.getOrder(), request.getOrderDetails());
        return result > 0 ? "订单创建成功！" : "订单创建失败！";
    }

    @PutMapping("/{id}")
    public String updateOrder(@PathVariable("id") Integer id, @RequestBody Order order) {
        order.setOrderId(id);
        int result = orderService.updateOrder(order);
        return result > 0 ? "订单更新成功！" : "订单更新失败！";
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") Integer id) {
        int result = orderService.deleteOrder(id);
        return result > 0 ? "订单删除成功！" : "订单删除失败！";
    }
}

// 用于接收创建订单请求的包装类
class OrderRequest {
    private Order order;
    private List<OrderDetail> orderDetails;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
} 