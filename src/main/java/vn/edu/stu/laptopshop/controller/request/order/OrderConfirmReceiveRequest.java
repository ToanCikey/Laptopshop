package vn.edu.stu.laptopshop.controller.request.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderConfirmReceiveRequest {
    @NotNull(message = "OrderId must be not null")
    @Min(value = 1, message = "OrderId must be at least 0")
    private Long orderId;

    @NotNull(message = "UserId must be not null")
    @Min(value = 1, message = "UserId must be at least 0")
    private Long userId;
}
