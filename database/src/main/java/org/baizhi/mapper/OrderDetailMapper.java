package org.baizhi.mapper;

import org.baizhi.model.OrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    @Select("SELECT * FROM orderdetails WHERE orderDetailId = #{orderDetailId}")
    OrderDetail getOrderDetailById(Integer orderDetailId);

    @Select("SELECT * FROM orderdetails WHERE orderId = #{orderId}")
    List<OrderDetail> getOrderDetailsByOrderId(Integer orderId);

    @Insert("INSERT INTO orderdetails (orderId, productId, quantity, subtotal) " +
            "VALUES (#{orderId}, #{productId}, #{quantity}, #{subtotal})")
    @Options(useGeneratedKeys = true, keyProperty = "orderDetailId")
    int insertOrderDetail(OrderDetail orderDetail);

    @Update("UPDATE orderdetails SET orderId = #{orderId}, productId = #{productId}, " +
            "quantity = #{quantity}, subtotal = #{subtotal} WHERE orderDetailId = #{orderDetailId}")
    int updateOrderDetail(OrderDetail orderDetail);

    @Delete("DELETE FROM orderdetails WHERE orderDetailId = #{orderDetailId}")
    int deleteOrderDetail(Integer orderDetailId);
} 