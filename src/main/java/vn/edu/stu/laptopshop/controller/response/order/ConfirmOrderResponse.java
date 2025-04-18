package vn.edu.stu.laptopshop.controller.response.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmOrderResponse {
    private Long orderId;
    private String orderStatus;
}
