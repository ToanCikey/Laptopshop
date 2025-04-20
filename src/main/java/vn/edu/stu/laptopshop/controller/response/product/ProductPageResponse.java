package vn.edu.stu.laptopshop.controller.response.product;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.laptopshop.controller.response.PageResponseAbstract;
import java.util.List;

@Getter
@Setter
public class ProductPageResponse extends PageResponseAbstract {
    private List<ProductResponse> products;
}
