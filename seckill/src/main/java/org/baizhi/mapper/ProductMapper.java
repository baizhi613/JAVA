package org.baizhi.mapper;

import org.apache.ibatis.annotations.*;
import org.baizhi.model.SeckillProduct;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Insert("INSERT INTO seckill_product (name, description, price, stock, start_time, end_time, create_time, update_time) " +
            "VALUES (#{name}, #{description}, #{price}, #{stock}, #{startTime}, #{endTime}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertProduct(SeckillProduct product);

    @Select("SELECT id,name,description,price,stock,start_time AS startTime,end_time AS endTime,create_time AS createTime,update_time AS updateTime FROM seckill_product")
    List<SeckillProduct> getAllProducts();

    @Select("SELECT id,name,description,price,stock,start_time AS startTime,end_time AS endTime,create_time AS createTime,update_time AS updateTime FROM seckill_product WHERE id = #{productId}")
    SeckillProduct getProductById(@Param("productId") Long productId);

    @Update("UPDATE seckill_product SET stock = stock - 1 WHERE id = #{productId} AND stock > 0")
    void decreaseStock(@Param("productId") Long productId);

    @Delete("DELETE FROM seckill_product WHERE id = #{productId}")
    void deleteProduct(Long productId);
}
