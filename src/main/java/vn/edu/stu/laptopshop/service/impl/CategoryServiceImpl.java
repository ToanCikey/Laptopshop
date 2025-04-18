package vn.edu.stu.laptopshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.stu.laptopshop.controller.request.category.CategoryCreateRequest;
import vn.edu.stu.laptopshop.controller.request.category.CategoryUpdateRequest;
import vn.edu.stu.laptopshop.exception.InvalidDataException;
import vn.edu.stu.laptopshop.exception.ResourceNotFoundException;
import vn.edu.stu.laptopshop.model.CategoryEntity;
import vn.edu.stu.laptopshop.repository.CategoryRepository;
import vn.edu.stu.laptopshop.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryEntity addCategory(CategoryCreateRequest request) {
        Optional<CategoryEntity> category = categoryRepository.findByName(request.getName());
        if (category.isPresent()){
            throw new InvalidDataException("Category name already exist");
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(request.getName());
        categoryRepository.save(categoryEntity);

        return categoryEntity;
    }

    @Override
    public void updateCategory(CategoryUpdateRequest request) {
        CategoryEntity category = getCategoryById(request.getId());
        if (category != null) {
            category.setName(request.getName());
            categoryRepository.save(category);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        CategoryEntity category = getCategoryById(id);
        if (category != null && category.getProducts().isEmpty()) {
            categoryRepository.delete(category);
        }else {
            throw new InvalidDataException("Cannot delete category because it contains product table constraints");
        }
    }

    @Override
    public CategoryEntity getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category id" + id + " not found"));
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }
}
