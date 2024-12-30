package org.baizhi.controller;

import org.baizhi.model.Product;
import org.baizhi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return product;
        } else {
            throw new RuntimeException("商品不存在！");
        }
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable("categoryId") Integer categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @PostMapping
    public String createProduct(@RequestBody Product product) {
        int result = productService.createProduct(product);
        return result > 0 ? "商品创建成功！" : "商品创建失败！";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        product.setProductId(id);
        int result = productService.updateProduct(product);
        return result > 0 ? "商品更新成功！" : "商品更新失败！";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        int result = productService.deleteProduct(id);
        return result > 0 ? "商品删除成功！" : "商品删除失败！";
    }

    @PutMapping("/{id}/stock")
    public String updateStock(@PathVariable("id") Integer id, @RequestParam Integer changeAmount) {
        int result = productService.updateStock(id, changeAmount);
        return result > 0 ? "库存更新成功！" : "库存更新失败！";
    }
} 