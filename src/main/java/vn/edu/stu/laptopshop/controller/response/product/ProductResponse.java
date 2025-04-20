package vn.edu.stu.laptopshop.controller.response.product;

import lombok.Getter;
import lombok.Setter;

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

    private Long brandId;

    private Long categoryId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
