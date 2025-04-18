package vn.edu.stu.laptopshop.controller.request.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequest {
    @NotNull(message = "Id must be not null")
    @Min(value = 0, message = "CategoryId must be at least 0")
    private Long id;

    @NotBlank(message = "Name must be not blank")
    @Size(min = 5, message = "Name minimum 5 characters")
    private String name;

    @NotNull(message = "Price must be not null")
    @Min(value = 0, message = "Price must be at least 0")
    private Double price;

    @NotBlank(message = "Description must be not blank")
    private String description;

    @NotBlank(message = "Image must be not blank")
    private String image;

    @NotNull(message = "StockQuantity must be not null")
    @Min(value = 0, message = "StockQuantity must be at least 0")
    private Long stockQuantity;

    @Min(value = 0, message = "BrandId must be at least 0")
    private Long brandId;

    @Min(value = 0, message = "CategoryId must be at least 0")
    private Long categoryId;
}
