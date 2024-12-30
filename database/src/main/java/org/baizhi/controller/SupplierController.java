package org.baizhi.controller;

import org.baizhi.model.Supplier;
import org.baizhi.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable("id") Integer id) {
        Supplier supplier = supplierService.getSupplierById(id);
        if (supplier != null) {
            return supplier;
        } else {
            throw new RuntimeException("供应商不存在！");
        }
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @PostMapping
    public String createSupplier(@RequestBody Supplier supplier) {
        int result = supplierService.createSupplier(supplier);
        return result > 0 ? "供应商创建成功！" : "供应商创建失败！";
    }

    @PutMapping("/{id}")
    public String updateSupplier(@PathVariable("id") Integer id, @RequestBody Supplier supplier) {
        supplier.setSupplierId(id);
        int result = supplierService.updateSupplier(supplier);
        return result > 0 ? "供应商更新成功！" : "供应商更新失败！";
    }

    @DeleteMapping("/{id}")
    public String deleteSupplier(@PathVariable("id") Integer id) {
        int result = supplierService.deleteSupplier(id);
        return result > 0 ? "供应商删除成功！" : "供应商删除失败！";
    }
} 