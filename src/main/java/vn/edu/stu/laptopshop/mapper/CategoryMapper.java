package vn.edu.stu.laptopshop.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.laptopshop.controller.response.CategoryResponse;
import vn.edu.stu.laptopshop.model.CategoryEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final ModelMapper modelMapper;

    public CategoryResponse toCategoryResponse(CategoryEntity category) {
        return modelMapper.map(category, CategoryResponse.class);
    }

    public List<CategoryResponse> toListCategoryResponse(List<CategoryEntity> categories) {
        return categories.stream().map(
                categoryEntity -> modelMapper.map(categoryEntity, CategoryResponse.class))
                .toList();
    }
}
