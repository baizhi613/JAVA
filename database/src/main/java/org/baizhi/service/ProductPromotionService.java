package org.baizhi.service;

import org.baizhi.model.ProductPromotion;
import java.util.List;

public interface ProductPromotionService {
    ProductPromotion getProductPromotionById(Integer productPromotionId);
    List<ProductPromotion> getProductPromotionsByProductId(Integer productId);
    List<ProductPromotion> getProductPromotionsByPromotionId(Integer promotionId);
    int createProductPromotion(ProductPromotion productPromotion);
    int deleteProductPromotion(Integer productPromotionId);
    int deleteProductPromotionsByProductId(Integer productId);
    int deleteProductPromotionsByPromotionId(Integer promotionId);
} 