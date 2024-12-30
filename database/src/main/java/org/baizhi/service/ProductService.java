package org.baizhi.service;

import org.baizhi.model.Product;
import java.util.List;

public interface ProductService {
    Product getProductById(Integer productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Integer categoryId);
    int createProduct(Product product);
    int updateProduct(Product product);
    int deleteProduct(Integer productId);
    int updateStock(Integer productId, Integer changeAmount);
} 