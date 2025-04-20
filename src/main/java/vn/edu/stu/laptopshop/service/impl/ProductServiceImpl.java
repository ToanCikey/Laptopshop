package vn.edu.stu.laptopshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.stu.laptopshop.controller.request.product.PriceRangeRequest;
import vn.edu.stu.laptopshop.controller.request.product.ProductCreateRequest;
import vn.edu.stu.laptopshop.controller.request.product.ProductUpdateRequest;
import vn.edu.stu.laptopshop.controller.response.product.ProductPageResponse;
import vn.edu.stu.laptopshop.exception.InvalidDataException;
import vn.edu.stu.laptopshop.exception.ResourceNotFoundException;
import vn.edu.stu.laptopshop.mapper.ProductMapper;
import vn.edu.stu.laptopshop.model.BrandEntity;
import vn.edu.stu.laptopshop.model.CategoryEntity;
import vn.edu.stu.laptopshop.model.ProductEntity;
import vn.edu.stu.laptopshop.repository.ProductRepository;
import vn.edu.stu.laptopshop.service.BrandService;
import vn.edu.stu.laptopshop.service.CategoryService;
import vn.edu.stu.laptopshop.service.ProductService;
import vn.edu.stu.laptopshop.specification.ProductSpecification;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

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

    @Override
    public ProductPageResponse getProductPageBySearch(List<String> brandNames, List<String> categoryNames, List<PriceRangeRequest> priceRanges, String sort, int page, int size) {
        Specification<ProductEntity> spec = Specification
                .where(ProductSpecification.filterByBrandNames(brandNames))
                .and(ProductSpecification.filterByCategoryNames(categoryNames))
                .and(ProductSpecification.filterByMultiplePriceRanges(priceRanges));
        Sort.Direction direction = ("desc".equalsIgnoreCase(sort)) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sortBy = Sort.by(direction, "price");

        Pageable pageable = PageRequest.of(page, size, sortBy);
        Page<ProductEntity> productPage = productRepository.findAll(spec, pageable);
        List<ProductEntity> productEntityList = productPage.getContent();

        ProductPageResponse productPageResponse = new ProductPageResponse();
        productPageResponse.setPageNumber(productPage.getNumber());
        productPageResponse.setPageSize(productPage.getSize());
        productPageResponse.setTotalElements(productPage.getTotalElements());
        productPageResponse.setTotalPages(productPage.getTotalPages());
        productPageResponse.setProducts(productMapper.toListProductResponse(productEntityList));

        return productPageResponse;
    }
}
