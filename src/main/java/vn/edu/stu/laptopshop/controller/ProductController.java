package vn.edu.stu.laptopshop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.stu.laptopshop.controller.request.product.ProductCreateRequest;
import vn.edu.stu.laptopshop.controller.response.ResponseSuccess;
import vn.edu.stu.laptopshop.model.ProductEntity;
import vn.edu.stu.laptopshop.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@Validated
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public ResponseSuccess<List<ProductEntity>> findAll(@RequestParam(required = false) String keyword) {
        List<ProductEntity> productEntities = productService.getAllProductsBySearch(keyword);
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Get all products success", productEntities);
    }

    @PostMapping(value = "/add")
    public ResponseSuccess<?> createProduct(@Valid @RequestBody ProductCreateRequest request){
        ProductEntity productEntity = productService.addProduct(request);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(), "Product created successfully", productEntity);
    }
}
