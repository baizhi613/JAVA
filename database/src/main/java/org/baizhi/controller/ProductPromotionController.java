package org.baizhi.controller;

import org.baizhi.model.ProductPromotion;
import org.baizhi.service.ProductPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productPromotions")
public class ProductPromotionController {

    @Autowired
    private ProductPromotionService productPromotionService;

    @GetMapping("/{id}")
    public ProductPromotion getProductPromotionById(@PathVariable("id") Integer id) {
        ProductPromotion productPromotion = productPromotionService.getProductPromotionById(id);
        if (productPromotion != null) {
            return productPromotion;
        } else {
            throw new RuntimeException("商品促销关系不存在！");
        }
    }

    @GetMapping("/product/{productId}")
    public List<ProductPromotion> getProductPromotionsByProductId(@PathVariable("productId") Integer productId) {
        return productPromotionService.getProductPromotionsByProductId(productId);
    }

    @GetMapping("/promotion/{promotionId}")
    public List<ProductPromotion> getProductPromotionsByPromotionId(@PathVariable("promotionId") Integer promotionId) {
        return productPromotionService.getProductPromotionsByPromotionId(promotionId);
    }

    @PostMapping
    public String createProductPromotion(@RequestBody ProductPromotion productPromotion) {
        int result = productPromotionService.createProductPromotion(productPromotion);
        return result > 0 ? "商品促销关系创建成功！" : "商品促销关系创建失败！";
    }

    @DeleteMapping("/{id}")
    public String deleteProductPromotion(@PathVariable("id") Integer id) {
        int result = productPromotionService.deleteProductPromotion(id);
        return result > 0 ? "商品促销关系删除成功！" : "商品促销关系删除失败！";
    }

    @DeleteMapping("/product/{productId}")
    public String deleteProductPromotionsByProductId(@PathVariable("productId") Integer productId) {
        int result = productPromotionService.deleteProductPromotionsByProductId(productId);
        return result > 0 ? "商品相关促销关系删除成功！" : "商品相关促销关系删除失败！";
    }

    @DeleteMapping("/promotion/{promotionId}")
    public String deleteProductPromotionsByPromotionId(@PathVariable("promotionId") Integer promotionId) {
        int result = productPromotionService.deleteProductPromotionsByPromotionId(promotionId);
        return result > 0 ? "促销活动相关商品关系删除成功！" : "促销活动相关商品关系删除失败！";
    }
} 