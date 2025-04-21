package vn.edu.stu.laptopshop.controller.request.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductFilterRequest {
    private String keyword;
    private List<String> brandNames;
    private List<String> categoryNames;
    private List<PriceRangeRequest> priceRanges;
    private String sort = "asc";
    private int page = 0;
    private int size = 10;
}
