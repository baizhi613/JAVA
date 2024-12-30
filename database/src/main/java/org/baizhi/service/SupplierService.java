package org.baizhi.service;

import org.baizhi.model.Supplier;
import java.util.List;

public interface SupplierService {
    Supplier getSupplierById(Integer supplierId);
    List<Supplier> getAllSuppliers();
    int createSupplier(Supplier supplier);
    int updateSupplier(Supplier supplier);
    int deleteSupplier(Integer supplierId);
} 