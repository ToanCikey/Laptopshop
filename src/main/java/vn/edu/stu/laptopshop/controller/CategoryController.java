package vn.edu.stu.laptopshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.stu.laptopshop.controller.request.category.CategoryCreateRequest;
import vn.edu.stu.laptopshop.controller.request.category.CategoryUpdateRequest;
import vn.edu.stu.laptopshop.controller.response.ResponseSuccess;
import vn.edu.stu.laptopshop.mapper.CategoryMapper;
import vn.edu.stu.laptopshop.model.CategoryEntity;
import vn.edu.stu.laptopshop.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Tag(name = "Category Controller")
@Slf4j(topic = "CATEGORY-CONTROLLER")
@Validated
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Operation(summary = "Create category", description = "API create category to database")
    @PostMapping("/create")
    public ResponseSuccess<?> createCategory(@Valid @RequestBody CategoryCreateRequest request) {
        CategoryEntity category = categoryService.addCategory(request);

        return new ResponseSuccess<>(HttpStatus.CREATED.value(),"Create category successful", categoryMapper.toCategoryResponse(category));
    }

    @Operation(summary = "Update category", description = "API update category to database")
    @PutMapping("/update")
    public ResponseSuccess<?> updateCategory(@Valid @RequestBody CategoryUpdateRequest request) {
        categoryService.updateCategory(request);

        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Update category successful");
    }

    @Operation(summary = "Delete brand", description = "API delete category to database")
    @DeleteMapping("/delete/{id}")
    public ResponseSuccess<?> deleteCategory(@Min(1) @PathVariable Long id) {
        categoryService.deleteCategory(id);

        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Delete category successful");
    }

    @Operation(summary = "GetAll category", description = "API get all category to database")
    @GetMapping("/list")
    public ResponseSuccess<?> getAllCategories() {
        List<CategoryEntity> categories = categoryService.getAllCategories();
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Getall categories to database", categoryMapper.toListCategoryResponse(categories));
    }
}
