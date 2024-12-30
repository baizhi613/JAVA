package org.baizhi.service.serviceImpl;

import org.baizhi.mapper.ProductMapper;
import org.baizhi.model.Product;
import org.baizhi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductById(Integer productId) {
        return productMapper.getProductById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }

    @Override
    public List<Product> getProductsByCategory(Integer categoryId) {
        return productMapper.getProductsByCategory(categoryId);
    }

    @Override
    public int createProduct(Product product) {
        return productMapper.insertProduct(product);
    }

    @Override
    public int updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    @Override
    public int deleteProduct(Integer productId) {
        return productMapper.deleteProduct(productId);
    }

    @Override
    @Transactional
    public int updateStock(Integer productId, Integer changeAmount) {
        return productMapper.updateStock(productId, changeAmount);
    }
} 