package org.baizhi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private Integer orderId;         // 订单ID
    private Integer userId;          // 用户ID
    private BigDecimal totalAmount;  // 总金额
    private LocalDateTime orderDate; // 订单日期
    private String status;           // 订单状态

    // Getters and Setters
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
} 