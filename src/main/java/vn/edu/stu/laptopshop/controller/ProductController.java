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
import vn.edu.stu.laptopshop.controller.request.product.ProductCreateRequest;
import vn.edu.stu.laptopshop.controller.request.product.ProductUpdateRequest;
import vn.edu.stu.laptopshop.controller.response.ResponseSuccess;
import vn.edu.stu.laptopshop.mapper.ProductMapper;
import vn.edu.stu.laptopshop.model.ProductEntity;
import vn.edu.stu.laptopshop.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@Validated
@Tag(name = "Product Controller")
@Slf4j(topic = "PRODUCT-CONTROLLER")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "Get all products", description = "API get all products to database")
    @GetMapping("/list")
    public ResponseSuccess<?> findAll(@RequestParam(required = false) String keyword) {
        List<ProductEntity> productEntities = productService.getAllProductsBySearch(keyword);
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Get all products success", productMapper.toListProductResponse(productEntities));
    }

    @Operation(summary = "Create product", description = "API create product to database")
    @PostMapping("/add")
    public ResponseSuccess<?> createProduct(@Valid @RequestBody ProductCreateRequest request){
        ProductEntity productEntity = productService.addProduct(request);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(), "Product created successfully", productMapper.toProductResponse(productEntity));
    }

    @Operation(summary = "Update product", description = "API update product to database")
    @PostMapping("/update")
    public ResponseSuccess<?> updateProduct(@Valid @RequestBody ProductUpdateRequest request){
        productService.updateProduct(request);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(), "Product updated successfully");
    }

    @Operation(summary = "Delete product", description = "API delete product to database")
    @PostMapping("/delete/{id}")
    public ResponseSuccess<?> deleteProduct(@Min(1) @PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(), "Product delete successfully");
    }
}
