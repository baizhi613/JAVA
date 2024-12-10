package org.baizhi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.baizhi.model.SeckillOrder;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM seckill_order")
    List<SeckillOrder> getAllOrders();

    @Select("SELECT * FROM seckill_order WHERE user_id = #{userId} AND product_id = #{productId}")
    SeckillOrder getOrderByUserAndProduct(@Param("userId") Long userId, @Param("productId") Long productId);

    @Insert("INSERT INTO seckill_order (user_id, product_id, order_status) VALUES (#{userId}, #{productId}, #{orderStatus})")
    void insertOrder(SeckillOrder order);
}