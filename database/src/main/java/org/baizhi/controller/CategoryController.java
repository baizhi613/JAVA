package org.baizhi.controller;

import org.baizhi.model.Category;
import org.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Integer id) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            return category;
        } else {
            throw new RuntimeException("分类不存在！");
        }
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public String createCategory(@RequestBody Category category) {
        int result = categoryService.createCategory(category);
        return result > 0 ? "分类创建成功！" : "分类创建失败！";
    }

    @PutMapping("/{id}")
    public String updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
        category.setCategoryId(id);
        int result = categoryService.updateCategory(category);
        return result > 0 ? "分类更新成功！" : "分类更新失败！";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        int result = categoryService.deleteCategory(id);
        return result > 0 ? "分类删除成功！" : "分类删除失败！";
    }
} 