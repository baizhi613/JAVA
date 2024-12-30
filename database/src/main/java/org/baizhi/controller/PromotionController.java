package org.baizhi.controller;

import org.baizhi.model.Promotion;
import org.baizhi.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/{id}")
    public Promotion getPromotionById(@PathVariable("id") Integer id) {
        Promotion promotion = promotionService.getPromotionById(id);
        if (promotion != null) {
            return promotion;
        } else {
            throw new RuntimeException("促销活动不存在！");
        }
    }

    @GetMapping
    public List<Promotion> getAllPromotions() {
        return promotionService.getAllPromotions();
    }

    @GetMapping("/active")
    public List<Promotion> getActivePromotions(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return promotionService.getActivePromotions(date);
    }

    @PostMapping
    public String createPromotion(@RequestBody Promotion promotion) {
        int result = promotionService.createPromotion(promotion);
        return result > 0 ? "促销活动创建成功！" : "促销活动创建失败！";
    }

    @PutMapping("/{id}")
    public String updatePromotion(@PathVariable("id") Integer id, @RequestBody Promotion promotion) {
        promotion.setPromotionId(id);
        int result = promotionService.updatePromotion(promotion);
        return result > 0 ? "促销活动更新成功！" : "促销活动更新失败！";
    }

    @DeleteMapping("/{id}")
    public String deletePromotion(@PathVariable("id") Integer id) {
        int result = promotionService.deletePromotion(id);
        return result > 0 ? "促销活动删除成功！" : "促销活动删除失败！";
    }
} 