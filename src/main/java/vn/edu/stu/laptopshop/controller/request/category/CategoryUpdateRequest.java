package vn.edu.stu.laptopshop.controller.request.category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateRequest {
    @NotNull(message = "Id must be not null")
    @Min(value = 1, message = "CategoryId must be at least 0")
    private Long id;

    @NotBlank(message = "Name must be not blank")
    @Size(min = 3, message = "Name minimum 3 characters")
    private String name;
}
