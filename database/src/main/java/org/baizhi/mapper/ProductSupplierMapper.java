package org.baizhi.mapper;

import org.baizhi.model.ProductSupplier;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductSupplierMapper {
    @Select("SELECT * FROM productsuppliers WHERE productSupplierId = #{productSupplierId}")
    ProductSupplier getProductSupplierById(Integer productSupplierId);

    @Select("SELECT * FROM productsuppliers WHERE productId = #{productId}")
    List<ProductSupplier> getProductSuppliersByProductId(Integer productId);

    @Select("SELECT * FROM productsuppliers WHERE supplierId = #{supplierId}")
    List<ProductSupplier> getProductSuppliersBySupplierId(Integer supplierId);

    @Insert("INSERT INTO productsuppliers (productId, supplierId, supplyPrice) " +
            "VALUES (#{productId}, #{supplierId}, #{supplyPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "productSupplierId")
    int insertProductSupplier(ProductSupplier productSupplier);

    @Update("UPDATE productsuppliers SET productId = #{productId}, supplierId = #{supplierId}, " +
            "supplyPrice = #{supplyPrice} WHERE productSupplierId = #{productSupplierId}")
    int updateProductSupplier(ProductSupplier productSupplier);

    @Delete("DELETE FROM productsuppliers WHERE productSupplierId = #{productSupplierId}")
    int deleteProductSupplier(Integer productSupplierId);

    @Delete("DELETE FROM productsuppliers WHERE productId = #{productId}")
    int deleteProductSuppliersByProductId(Integer productId);

    @Delete("DELETE FROM productsuppliers WHERE supplierId = #{supplierId}")
    int deleteProductSuppliersBySupplierId(Integer supplierId);
} 