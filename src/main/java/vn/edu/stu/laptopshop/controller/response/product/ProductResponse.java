package vn.edu.stu.laptopshop.controller.response.product;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.laptopshop.controller.response.brand.BrandResponse;
import vn.edu.stu.laptopshop.controller.response.category.CategoryResponse;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Long stockQuantity;

    private String imageUrl;

    private BrandResponse brand;

    private CategoryResponse category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
