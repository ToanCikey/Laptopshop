package vn.edu.stu.laptopshop.controller.request.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderCreateRequest {
    @NotNull(message = "UserId must be not null")
    @Min(value = 1, message = "UserId must be at least 0")
    private Long userId;

    @NotBlank(message = "ReceiverName must be not blank")
    @Size(min = 3, message = "ReceiverName minimum 3 characters")
    private String receiverName;

    @NotBlank(message = "ReceiverPhone must be not blank")
    @Size(min = 10, message = "ReceiverPhone minimum 10 characters")
    private String receiverPhone;

    @NotBlank(message = "ReceiverAddress must be not blank")
    @Size(min = 10, message = "ReceiverAddress minimum 10 characters")
    private String receiverAddress;

    @NotNull(message = "Items must be not null")
    private List<OrderItemRequest> items;
}
