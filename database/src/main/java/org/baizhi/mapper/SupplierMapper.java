package org.baizhi.mapper;

import org.baizhi.model.Supplier;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SupplierMapper {
    @Select("SELECT * FROM suppliers WHERE supplierId = #{supplierId}")
    Supplier getSupplierById(Integer supplierId);

    @Select("SELECT * FROM suppliers")
    List<Supplier> getAllSuppliers();

    @Insert("INSERT INTO suppliers (supplierName, contactName, phone, address) " +
            "VALUES (#{supplierName}, #{contactName}, #{phone}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "supplierId")
    int insertSupplier(Supplier supplier);

    @Update("UPDATE suppliers SET supplierName = #{supplierName}, contactName = #{contactName}, " +
            "phone = #{phone}, address = #{address} WHERE supplierId = #{supplierId}")
    int updateSupplier(Supplier supplier);

    @Delete("DELETE FROM suppliers WHERE supplierId = #{supplierId}")
    int deleteSupplier(Integer supplierId);
} 