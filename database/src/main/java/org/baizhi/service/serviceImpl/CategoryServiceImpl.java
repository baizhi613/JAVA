package org.baizhi.service.serviceImpl;

import org.baizhi.mapper.CategoryMapper;
import org.baizhi.model.Category;
import org.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryMapper.getCategoryById(categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.getAllCategories();
    }

    @Override
    public int createCategory(Category category) {
        return categoryMapper.insertCategory(category);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }

    @Override
    public int deleteCategory(Integer categoryId) {
        return categoryMapper.deleteCategory(categoryId);
    }
} 