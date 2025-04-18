package vn.edu.stu.laptopshop.service;

import vn.edu.stu.laptopshop.controller.request.brand.BrandCreateRequest;
import vn.edu.stu.laptopshop.controller.request.brand.BrandUpdateRequest;
import vn.edu.stu.laptopshop.model.BrandEntity;

import java.util.List;

public interface BrandService {
    BrandEntity addBrand(BrandCreateRequest request);
    void updateBrand(BrandUpdateRequest request);
    void deleteBrand(Long id);
    BrandEntity getBrandById(Long id);
    List<BrandEntity> getAllBrands();
}
