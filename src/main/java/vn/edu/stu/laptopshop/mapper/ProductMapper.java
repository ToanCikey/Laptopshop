package vn.edu.stu.laptopshop.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.laptopshop.controller.response.product.ProductResponse;
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
            productResponse.setBrandId(productEntity.getBrand().getId());
        }
        if(productEntity.getCategory() != null){
            productResponse.setCategoryId(productEntity.getCategory().getId());
        }
        return productResponse;
    }

    public List<ProductResponse> toListProductResponse(List<ProductEntity> productEntityList) {
        return productEntityList.stream().map(
                productEntity -> {
                    ProductResponse productResponse = new ProductResponse();
                    modelMapper.map(productEntity, productResponse);
                    if(productEntity.getBrand() != null){
                        productResponse.setBrandId(productEntity.getBrand().getId());
                    }
                    if(productEntity.getCategory() != null){
                        productResponse.setCategoryId(productEntity.getCategory().getId());
                    }
                    return productResponse;
                }).toList();
    }
}
