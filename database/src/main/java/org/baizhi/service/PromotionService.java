package org.baizhi.service;

import org.baizhi.model.Promotion;
import java.time.LocalDate;
import java.util.List;

public interface PromotionService {
    Promotion getPromotionById(Integer promotionId);
    List<Promotion> getAllPromotions();
    List<Promotion> getActivePromotions(LocalDate date);
    int createPromotion(Promotion promotion);
    int updatePromotion(Promotion promotion);
    int deletePromotion(Integer promotionId);
} 