package vn.edu.stu.laptopshop.service;

import vn.edu.stu.laptopshop.controller.request.product.ProductCreateRequest;
import vn.edu.stu.laptopshop.model.ProductEntity;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductEntity addProduct(ProductCreateRequest product);
    List<ProductEntity> getAllProductsBySearch(String keyword);
}
