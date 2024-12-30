package org.baizhi.model;

import java.time.LocalDateTime;

public class StockRecord {
    private Integer stockRecordId;    // 库存记录ID
    private Integer productId;        // 商品ID
    private String productName;       // 商品名称
    private String type;              // 变动类型（In/Out）
    private Integer quantity;         // 变动数量
    private String note;              // 备注
    private LocalDateTime date;       // 日期

    // Getters and Setters
    public Integer getStockRecordId() {
        return stockRecordId;
    }

    public void setStockRecordId(Integer stockRecordId) {
        this.stockRecordId = stockRecordId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
} 