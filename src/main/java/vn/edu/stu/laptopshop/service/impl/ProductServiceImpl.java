package vn.edu.stu.laptopshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.stu.laptopshop.controller.request.product.ProductCreateRequest;
import vn.edu.stu.laptopshop.exception.InvalidDataException;
import vn.edu.stu.laptopshop.model.ProductEntity;
import vn.edu.stu.laptopshop.repository.ProductRepository;
import vn.edu.stu.laptopshop.service.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductEntity addProduct(ProductCreateRequest product) {
        Optional<ProductEntity> productOptinal = productRepository.findByName(product.getName());
        if (productOptinal.isPresent()) {
            throw new InvalidDataException("Product with " + product.getName() + " already exists");
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setQuantity(product.getQuantity());
        productEntity.setBrand(product.getBrand());
        productEntity.setDescription(product.getDescription());
        productEntity.setImage(product.getImage());

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
}
