package org.baizhi.service.serviceImpl;

import org.baizhi.mapper.ProductPromotionMapper;
import org.baizhi.model.ProductPromotion;
import org.baizhi.service.ProductPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPromotionServiceImpl implements ProductPromotionService {

    @Autowired
    private ProductPromotionMapper productPromotionMapper;

    @Override
    public ProductPromotion getProductPromotionById(Integer productPromotionId) {
        return productPromotionMapper.getProductPromotionById(productPromotionId);
    }

    @Override
    public List<ProductPromotion> getProductPromotionsByProductId(Integer productId) {
        return productPromotionMapper.getProductPromotionsByProductId(productId);
    }

    @Override
    public List<ProductPromotion> getProductPromotionsByPromotionId(Integer promotionId) {
        return productPromotionMapper.getProductPromotionsByPromotionId(promotionId);
    }

    @Override
    public int createProductPromotion(ProductPromotion productPromotion) {
        return productPromotionMapper.insertProductPromotion(productPromotion);
    }

    @Override
    public int deleteProductPromotion(Integer productPromotionId) {
        return productPromotionMapper.deleteProductPromotion(productPromotionId);
    }

    @Override
    public int deleteProductPromotionsByProductId(Integer productId) {
        return productPromotionMapper.deleteProductPromotionsByProductId(productId);
    }

    @Override
    public int deleteProductPromotionsByPromotionId(Integer promotionId) {
        return productPromotionMapper.deleteProductPromotionsByPromotionId(promotionId);
    }
} 