package org.baizhi.service;

import org.baizhi.model.ProductSupplier;
import java.util.List;

public interface ProductSupplierService {
    ProductSupplier getProductSupplierById(Integer productSupplierId);
    List<ProductSupplier> getProductSuppliersByProductId(Integer productId);
    List<ProductSupplier> getProductSuppliersBySupplierId(Integer supplierId);
    int createProductSupplier(ProductSupplier productSupplier);
    int updateProductSupplier(ProductSupplier productSupplier);
    int deleteProductSupplier(Integer productSupplierId);
    int deleteProductSuppliersByProductId(Integer productId);
    int deleteProductSuppliersBySupplierId(Integer supplierId);
} 