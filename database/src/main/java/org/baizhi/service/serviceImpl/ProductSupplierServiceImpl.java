package org.baizhi.service.serviceImpl;

import org.baizhi.mapper.ProductSupplierMapper;
import org.baizhi.model.ProductSupplier;
import org.baizhi.service.ProductSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSupplierServiceImpl implements ProductSupplierService {

    @Autowired
    private ProductSupplierMapper productSupplierMapper;

    @Override
    public ProductSupplier getProductSupplierById(Integer productSupplierId) {
        return productSupplierMapper.getProductSupplierById(productSupplierId);
    }

    @Override
    public List<ProductSupplier> getProductSuppliersByProductId(Integer productId) {
        return productSupplierMapper.getProductSuppliersByProductId(productId);
    }

    @Override
    public List<ProductSupplier> getProductSuppliersBySupplierId(Integer supplierId) {
        return productSupplierMapper.getProductSuppliersBySupplierId(supplierId);
    }

    @Override
    public int createProductSupplier(ProductSupplier productSupplier) {
        return productSupplierMapper.insertProductSupplier(productSupplier);
    }

    @Override
    public int updateProductSupplier(ProductSupplier productSupplier) {
        return productSupplierMapper.updateProductSupplier(productSupplier);
    }

    @Override
    public int deleteProductSupplier(Integer productSupplierId) {
        return productSupplierMapper.deleteProductSupplier(productSupplierId);
    }

    @Override
    public int deleteProductSuppliersByProductId(Integer productId) {
        return productSupplierMapper.deleteProductSuppliersByProductId(productId);
    }

    @Override
    public int deleteProductSuppliersBySupplierId(Integer supplierId) {
        return productSupplierMapper.deleteProductSuppliersBySupplierId(supplierId);
    }
} 