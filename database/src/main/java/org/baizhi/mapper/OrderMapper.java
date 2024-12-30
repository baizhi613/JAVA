package org.baizhi.mapper;

import org.baizhi.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("SELECT * FROM orders WHERE orderId = #{orderId}")
    Order getOrderById(Integer orderId);

    @Select("SELECT * FROM orders")
    List<Order> getAllOrders();

    @Insert("INSERT INTO orders (userId, totalAmount, orderDate, status) " +
            "VALUES (#{userId}, #{totalAmount}, #{orderDate}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    int insertOrder(Order order);

    @Update("UPDATE orders SET userId = #{userId}, totalAmount = #{totalAmount}, " +
            "orderDate = #{orderDate}, status = #{status} WHERE orderId = #{orderId}")
    int updateOrder(Order order);

    @Delete("DELETE FROM orders WHERE orderId = #{orderId}")
    int deleteOrder(Integer orderId);
} 