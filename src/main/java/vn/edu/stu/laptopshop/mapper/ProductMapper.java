package vn.edu.stu.laptopshop.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.laptopshop.controller.response.brand.BrandResponse;
import vn.edu.stu.laptopshop.controller.response.category.CategoryResponse;
import vn.edu.stu.laptopshop.controller.response.product.ProductResponse;
import vn.edu.stu.laptopshop.model.BrandEntity;
import vn.edu.stu.laptopshop.model.CategoryEntity;
import vn.edu.stu.laptopshop.model.ProductEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductResponse toProductResponse(ProductEntity productEntity) {
        ProductResponse productResponse = new ProductResponse();
        modelMapper.map(productEntity, productResponse);
        if(productEntity.getBrand() != null){
            BrandEntity brandEntity = productEntity.getBrand();
            productResponse.setBrand(modelMapper.map(brandEntity, BrandResponse.class));
        }
        if(productEntity.getCategory() != null){
           CategoryEntity categoryEntity = productEntity.getCategory();
           productResponse.setCategory(modelMapper.map(categoryEntity, CategoryResponse.class));
        }
        return productResponse;
    }

    public List<ProductResponse> toListProductResponse(List<ProductEntity> productEntityList) {
        return productEntityList.stream().map(
                productEntity -> {
                    ProductResponse productResponse = new ProductResponse();
                    modelMapper.map(productEntity, productResponse);
                    if(productEntity.getBrand() != null){
                        BrandEntity brandEntity = productEntity.getBrand();
                        productResponse.setBrand(modelMapper.map(brandEntity, BrandResponse.class));
                    }
                    if(productEntity.getCategory() != null){
                        CategoryEntity categoryEntity = productEntity.getCategory();
                        productResponse.setCategory(modelMapper.map(categoryEntity, CategoryResponse.class));
                    }
                    return productResponse;
                }).toList();
    }
}
