package vn.edu.stu.laptopshop.controller.request.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmOrderRequest {
    @NotNull(message = "OrderId must be not null")
    @Min(value = 1, message = "OrderId must be at least 0")
    private Long orderId;

    @NotNull(message = "AdminId must be not null")
    @Min(value = 1, message = "AdminId must be at least 0")
    private Long adminId;
}
