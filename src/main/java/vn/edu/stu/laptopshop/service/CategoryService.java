package vn.edu.stu.laptopshop.service;

import vn.edu.stu.laptopshop.controller.request.category.CategoryCreateRequest;
import vn.edu.stu.laptopshop.controller.request.category.CategoryUpdateRequest;
import vn.edu.stu.laptopshop.model.CategoryEntity;

import java.util.List;

public interface CategoryService {
    CategoryEntity addCategory(CategoryCreateRequest request);
    void updateCategory(CategoryUpdateRequest request);
    void deleteCategory(Long id);
    CategoryEntity getCategoryById(Long id);
    List<CategoryEntity> getAllCategories();
}
