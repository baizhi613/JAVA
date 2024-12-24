package org.baizhi.controller;

import org.baizhi.model.SeckillOrder;
import org.baizhi.service.OrderService;
import org.baizhi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seckill/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 秒杀商品接口
    @PostMapping("/orderproduct")
    public String placeOrder(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) {
        try {
            orderService.placeOrder(userId, productId);
            return "秒杀成功！";
        } catch (Exception e) {
            return "秒杀失败：" + e.getMessage();
        }
    }

    // 获取所有订单
    @GetMapping("/allorders")
    public List<SeckillOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/deleteall")
    public String deleteAllOrders() {
        try {
            orderService.deleteAllOrders();
            return "所有订单删除成功！";
        } catch (Exception e) {
            return "订单删除失败：" + e.getMessage();
        }
    }
}
