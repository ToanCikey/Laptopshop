package vn.edu.stu.laptopshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.stu.laptopshop.controller.request.product.ProductCreateRequest;
import vn.edu.stu.laptopshop.controller.request.product.ProductUpdateRequest;
import vn.edu.stu.laptopshop.exception.InvalidDataException;
import vn.edu.stu.laptopshop.exception.ResourceNotFoundException;
import vn.edu.stu.laptopshop.model.BrandEntity;
import vn.edu.stu.laptopshop.model.CategoryEntity;
import vn.edu.stu.laptopshop.model.ProductEntity;
import vn.edu.stu.laptopshop.repository.ProductRepository;
import vn.edu.stu.laptopshop.service.BrandService;
import vn.edu.stu.laptopshop.service.CategoryService;
import vn.edu.stu.laptopshop.service.ProductService;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;

    @Override
    public ProductEntity addProduct(ProductCreateRequest request) {
        Optional<ProductEntity> productOptinal = productRepository.findByName(request.getName());
        if (productOptinal.isPresent()) {
            throw new InvalidDataException("Product with " + request.getName() + " already exists");
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(request.getName());
        productEntity.setPrice(request.getPrice());
        productEntity.setImageUrl(request.getImage());
        productEntity.setDescription(request.getDescription());
        productEntity.setStockQuantity(request.getStockQuantity());

        if(request.getBrandId() != null){
            BrandEntity brandEntity = brandService.getBrandById(request.getBrandId());
            productEntity.setBrand(brandEntity);
        }
        if(request.getCategoryId() != null){
            CategoryEntity categoryEntity = categoryService.getCategoryById(request.getCategoryId());
            productEntity.setCategory(categoryEntity);
        }
        productRepository.save(productEntity);

        return productEntity;
    }

    @Override
    public List<ProductEntity> getAllProductsBySearch(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return productRepository.findAll();
        }
        return productRepository.getAllBySearch(keyword);
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity productEntity = getProductById(id);
        if (productEntity != null && productEntity.getOrderDetails().isEmpty()) {
            productRepository.delete(productEntity);
        }else{
            throw new InvalidDataException("Cannot delete product because it contains order_detail table constraints");
        }
    }

    @Override
    public void updateProduct(ProductUpdateRequest request) {
        ProductEntity productEntity = getProductById(request.getId());
        if (productEntity != null) {
            productEntity.setName(request.getName());
            productEntity.setPrice(request.getPrice());
            productEntity.setDescription(request.getDescription());
            productEntity.setStockQuantity(request.getStockQuantity());
            productEntity.setImageUrl(request.getImage());

            if(request.getBrandId() != null){
                BrandEntity brandEntity = brandService.getBrandById(request.getBrandId());
                productEntity.setBrand(brandEntity);
            }
            if(request.getCategoryId() != null){
                CategoryEntity categoryEntity = categoryService.getCategoryById(request.getCategoryId());
                productEntity.setCategory(categoryEntity);
            }
            productRepository.save(productEntity);
        }
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product with id " + id + " not found"));
    }
}
