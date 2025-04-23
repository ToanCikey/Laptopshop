package vn.edu.stu.laptopshop.controller.response.order;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.laptopshop.common.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Long id;

    private LocalDateTime orderDate;

    private Double totalAmount;

    private OrderStatus status;

    private String reason;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long userId;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private List<OrderDetailResponse> orderDetails;
}
