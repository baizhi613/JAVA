package org.baizhi.mapper;

import org.baizhi.model.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("SELECT * FROM categories WHERE categoryId = #{categoryId}")
    Category getCategoryById(Integer categoryId);

    @Select("SELECT * FROM categories")
    List<Category> getAllCategories();

    @Insert("INSERT INTO categories (categoryName, description, createdAt) " +
            "VALUES (#{categoryName}, #{description}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "categoryId")
    int insertCategory(Category category);

    @Update("UPDATE categories SET categoryName = #{categoryName}, description = #{description} WHERE categoryId = #{categoryId}")
    int updateCategory(Category category);

    @Delete("DELETE FROM categories WHERE categoryId = #{categoryId}")
    int deleteCategory(Integer categoryId);
} 