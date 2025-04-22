package vn.edu.stu.laptopshop.controller.response.order;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDetailResponse {
    private Long id;

    private Long quantity;

    private Double unitPrice;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long orderId;

    private Long productId;

    private String productName;
}
