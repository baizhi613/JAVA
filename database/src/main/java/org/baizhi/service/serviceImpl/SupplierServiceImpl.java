package org.baizhi.service.serviceImpl;

import org.baizhi.mapper.SupplierMapper;
import org.baizhi.model.Supplier;
import org.baizhi.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public Supplier getSupplierById(Integer supplierId) {
        return supplierMapper.getSupplierById(supplierId);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierMapper.getAllSuppliers();
    }

    @Override
    public int createSupplier(Supplier supplier) {
        return supplierMapper.insertSupplier(supplier);
    }

    @Override
    public int updateSupplier(Supplier supplier) {
        return supplierMapper.updateSupplier(supplier);
    }

    @Override
    public int deleteSupplier(Integer supplierId) {
        return supplierMapper.deleteSupplier(supplierId);
    }
} 