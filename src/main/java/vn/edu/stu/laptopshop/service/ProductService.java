package vn.edu.stu.laptopshop.service;

import vn.edu.stu.laptopshop.controller.request.product.ProductCreateRequest;
import vn.edu.stu.laptopshop.controller.request.product.ProductFilterRequest;
import vn.edu.stu.laptopshop.controller.request.product.ProductUpdateRequest;
import vn.edu.stu.laptopshop.controller.response.product.ProductPageResponse;
import vn.edu.stu.laptopshop.model.ProductEntity;
import java.util.List;

public interface ProductService {
    ProductEntity addProduct(ProductCreateRequest request);
    List<ProductEntity> getAllProductsBySearch(String keyword);
    void deleteProduct(Long id);
    void updateProduct(ProductUpdateRequest request);
    ProductEntity getProductById(Long id);
    ProductPageResponse getProductPageBySearch(ProductFilterRequest request);
}
