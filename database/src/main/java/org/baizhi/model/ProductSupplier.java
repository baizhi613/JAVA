package org.baizhi.model;

import java.math.BigDecimal;

public class ProductSupplier {
    private Integer productSupplierId;  // 商品供应商ID
    private Integer productId;          // 商品ID
    private Integer supplierId;         // 供应商ID
    private BigDecimal supplyPrice;     // 供应价格

    // Getters and Setters
    public Integer getProductSupplierId() {
        return productSupplierId;
    }

    public void setProductSupplierId(Integer productSupplierId) {
        this.productSupplierId = productSupplierId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(BigDecimal supplyPrice) {
        this.supplyPrice = supplyPrice;
    }
} 