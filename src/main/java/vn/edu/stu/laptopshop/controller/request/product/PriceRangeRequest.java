package vn.edu.stu.laptopshop.controller.request.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceRangeRequest {
    private Double min;
    private Double max;
}
