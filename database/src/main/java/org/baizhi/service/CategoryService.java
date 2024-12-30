package org.baizhi.service;

import org.baizhi.model.Category;
import java.util.List;

public interface CategoryService {
    Category getCategoryById(Integer categoryId);
    List<Category> getAllCategories();
    int createCategory(Category category);
    int updateCategory(Category category);
    int deleteCategory(Integer categoryId);
} 