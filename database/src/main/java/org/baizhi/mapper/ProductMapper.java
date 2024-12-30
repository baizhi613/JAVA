package org.baizhi.mapper;

import org.baizhi.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM products WHERE productId = #{productId}")
    Product getProductById(Integer productId);

    @Select("SELECT * FROM products")
    List<Product> getAllProducts();

    @Select("SELECT * FROM products WHERE categoryId = #{categoryId}")
    List<Product> getProductsByCategory(Integer categoryId);

    @Insert("INSERT INTO products (productName, categoryId, price, stock, createdAt) " +
            "VALUES (#{productName}, #{categoryId}, #{price}, #{stock}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "productId")
    int insertProduct(Product product);

    @Update("UPDATE products SET productName = #{productName}, categoryId = #{categoryId}, " +
            "price = #{price}, stock = #{stock} WHERE productId = #{productId}")
    int updateProduct(Product product);

    @Delete("DELETE FROM products WHERE productId = #{productId}")
    int deleteProduct(Integer productId);

    @Update("UPDATE products SET stock = stock + #{changeAmount} WHERE productId = #{productId}")
    int updateStock(@Param("productId") Integer productId, @Param("changeAmount") Integer changeAmount);
} 