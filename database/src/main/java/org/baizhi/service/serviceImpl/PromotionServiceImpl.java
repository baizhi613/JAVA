package org.baizhi.service.serviceImpl;

import org.baizhi.mapper.PromotionMapper;
import org.baizhi.model.Promotion;
import org.baizhi.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionMapper promotionMapper;

    @Override
    public Promotion getPromotionById(Integer promotionId) {
        return promotionMapper.getPromotionById(promotionId);
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionMapper.getAllPromotions();
    }

    @Override
    public List<Promotion> getActivePromotions(LocalDate date) {
        return promotionMapper.getActivePromotions(date);
    }

    @Override
    public int createPromotion(Promotion promotion) {
        return promotionMapper.insertPromotion(promotion);
    }

    @Override
    public int updatePromotion(Promotion promotion) {
        return promotionMapper.updatePromotion(promotion);
    }

    @Override
    public int deletePromotion(Integer promotionId) {
        return promotionMapper.deletePromotion(promotionId);
    }
} 