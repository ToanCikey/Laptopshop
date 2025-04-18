package vn.edu.stu.laptopshop.controller.request.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderCreateRequest {
    @NotNull(message = "UserId must be not null")
    @Min(value = 1, message = "UserId must be at least 0")
    private Long userId;

    @NotNull(message = "Items must be not null")
    private List<OrderItemRequest> items;
}
