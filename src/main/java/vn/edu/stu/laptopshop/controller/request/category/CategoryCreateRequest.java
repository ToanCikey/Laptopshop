package vn.edu.stu.laptopshop.controller.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateRequest {
    @NotBlank(message = "Name must be not blank")
    @Size(min = 3, message = "Name minimum 3 characters")
    private String name;
}
