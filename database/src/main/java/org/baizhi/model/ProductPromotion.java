package org.baizhi.model;

public class ProductPromotion {
    private Integer productPromotionId;  // 商品促销ID
    private Integer productId;           // 商品ID
    private Integer promotionId;         // 促销ID

    // Getters and Setters
    public Integer getProductPromotionId() {
        return productPromotionId;
    }

    public void setProductPromotionId(Integer productPromotionId) {
        this.productPromotionId = productPromotionId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }
} 