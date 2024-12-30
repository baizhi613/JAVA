package org.baizhi.mapper;

import org.baizhi.model.ProductPromotion;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductPromotionMapper {
    @Select("SELECT * FROM productpromotions WHERE productPromotionId = #{productPromotionId}")
    ProductPromotion getProductPromotionById(Integer productPromotionId);

    @Select("SELECT * FROM productpromotions WHERE productId = #{productId}")
    List<ProductPromotion> getProductPromotionsByProductId(Integer productId);

    @Select("SELECT * FROM productpromotions WHERE promotionId = #{promotionId}")
    List<ProductPromotion> getProductPromotionsByPromotionId(Integer promotionId);

    @Insert("INSERT INTO productpromotions (productId, promotionId) VALUES (#{productId}, #{promotionId})")
    @Options(useGeneratedKeys = true, keyProperty = "productPromotionId")
    int insertProductPromotion(ProductPromotion productPromotion);

    @Delete("DELETE FROM productpromotions WHERE productPromotionId = #{productPromotionId}")
    int deleteProductPromotion(Integer productPromotionId);

    @Delete("DELETE FROM productpromotions WHERE productId = #{productId}")
    int deleteProductPromotionsByProductId(Integer productId);

    @Delete("DELETE FROM productpromotions WHERE promotionId = #{promotionId}")
    int deleteProductPromotionsByPromotionId(Integer promotionId);
} 