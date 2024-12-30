package org.baizhi.controller;

import org.baizhi.model.ProductSupplier;
import org.baizhi.service.ProductSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productSuppliers")
public class ProductSupplierController {

    @Autowired
    private ProductSupplierService productSupplierService;

    @GetMapping("/{id}")
    public ProductSupplier getProductSupplierById(@PathVariable("id") Integer id) {
        ProductSupplier productSupplier = productSupplierService.getProductSupplierById(id);
        if (productSupplier != null) {
            return productSupplier;
        } else {
            throw new RuntimeException("商品供应商关系不存在！");
        }
    }

    @GetMapping("/product/{productId}")
    public List<ProductSupplier> getProductSuppliersByProductId(@PathVariable("productId") Integer productId) {
        return productSupplierService.getProductSuppliersByProductId(productId);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<ProductSupplier> getProductSuppliersBySupplierId(@PathVariable("supplierId") Integer supplierId) {
        return productSupplierService.getProductSuppliersBySupplierId(supplierId);
    }

    @PostMapping
    public String createProductSupplier(@RequestBody ProductSupplier productSupplier) {
        int result = productSupplierService.createProductSupplier(productSupplier);
        return result > 0 ? "商品供应商关系创建成功！" : "商品供应商关系创建失败！";
    }

    @PutMapping("/{id}")
    public String updateProductSupplier(@PathVariable("id") Integer id, @RequestBody ProductSupplier productSupplier) {
        productSupplier.setProductSupplierId(id);
        int result = productSupplierService.updateProductSupplier(productSupplier);
        return result > 0 ? "商品供应商关系更新成功！" : "商品供应商关系更新失败！";
    }

    @DeleteMapping("/{id}")
    public String deleteProductSupplier(@PathVariable("id") Integer id) {
        int result = productSupplierService.deleteProductSupplier(id);
        return result > 0 ? "商品供应商关系删除成功！" : "商品供应商关系删除失败！";
    }

    @DeleteMapping("/product/{productId}")
    public String deleteProductSuppliersByProductId(@PathVariable("productId") Integer productId) {
        int result = productSupplierService.deleteProductSuppliersByProductId(productId);
        return result > 0 ? "商品相关供应商关系删除成功！" : "商品相关供应商关系删除失败！";
    }

    @DeleteMapping("/supplier/{supplierId}")
    public String deleteProductSuppliersBySupplierId(@PathVariable("supplierId") Integer supplierId) {
        int result = productSupplierService.deleteProductSuppliersBySupplierId(supplierId);
        return result > 0 ? "供应商相关商品关系删除成功！" : "供应商相关商品关系删除失败！";
    }
} 