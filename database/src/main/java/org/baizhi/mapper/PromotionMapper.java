package org.baizhi.mapper;

import org.baizhi.model.Promotion;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PromotionMapper {
    @Select("SELECT * FROM promotions WHERE promotionId = #{promotionId}")
    Promotion getPromotionById(Integer promotionId);

    @Select("SELECT * FROM promotions")
    List<Promotion> getAllPromotions();

    @Select("SELECT * FROM promotions WHERE startDate <= #{date} AND endDate >= #{date}")
    List<Promotion> getActivePromotions(LocalDate date);

    @Insert("INSERT INTO promotions (promotionName, discountRate, startDate, endDate) " +
            "VALUES (#{promotionName}, #{discountRate}, #{startDate}, #{endDate})")
    @Options(useGeneratedKeys = true, keyProperty = "promotionId")
    int insertPromotion(Promotion promotion);

    @Update("UPDATE promotions SET promotionName = #{promotionName}, discountRate = #{discountRate}, " +
            "startDate = #{startDate}, endDate = #{endDate} WHERE promotionId = #{promotionId}")
    int updatePromotion(Promotion promotion);

    @Delete("DELETE FROM promotions WHERE promotionId = #{promotionId}")
    int deletePromotion(Integer promotionId);
} 