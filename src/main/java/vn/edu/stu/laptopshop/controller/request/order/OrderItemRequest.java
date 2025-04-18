package vn.edu.stu.laptopshop.controller.request.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    @NotNull(message = "ProductId must be not null")
    @Min(value = 1, message = "ProductId must be at least 0")
    private Long productId;

    @NotNull(message = "Quantity must be not null")
    @Min(value = 1, message = "Quantity must be at least 0")
    private Long quantity;
}
