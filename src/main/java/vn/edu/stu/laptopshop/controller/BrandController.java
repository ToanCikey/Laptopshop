package vn.edu.stu.laptopshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.stu.laptopshop.controller.request.brand.BrandCreateRequest;
import vn.edu.stu.laptopshop.controller.request.brand.BrandUpdateRequest;
import vn.edu.stu.laptopshop.controller.response.ResponseSuccess;
import vn.edu.stu.laptopshop.mapper.BrandMapper;
import vn.edu.stu.laptopshop.model.BrandEntity;
import vn.edu.stu.laptopshop.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
@Tag(name = "Brand Controller")
@Slf4j(topic = "BRAND-CONTROLLER")
@Validated
public class BrandController {
    private final BrandService brandService;
    private final BrandMapper brandMapper;

    @Operation(summary = "Create Brand", description = "API create brand to database")
    @PostMapping("/create")
    public ResponseSuccess<?> createBrand(@Valid @RequestBody BrandCreateRequest request) {
        BrandEntity entity = brandService.addBrand(request);

        return new ResponseSuccess<>(HttpStatus.CREATED.value(),"Create brand successful", brandMapper.toBrandResponse(entity));
    }

    @Operation(summary = "Update Brand", description = "API update brand to database")
    @PostMapping("/update")
    public ResponseSuccess<?> updateBrand(@Valid @RequestBody BrandUpdateRequest request) {
        brandService.updateBrand(request);

        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Update brand successful");
    }

    @Operation(summary = "Delete brand", description = "API delete brand to database")
    @PostMapping("/delete/{id}")
    public ResponseSuccess<?> deleteBrand(@Min(1) @PathVariable Long id) {
        brandService.deleteBrand(id);

        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Delete brand successful");
    }

    @Operation(summary = "GetAll brand", description = "API get all brand to database")
    @GetMapping("/list")
    public ResponseSuccess<?> getAllBrands() {
        List<BrandEntity> brands = brandService.getAllBrands();
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Getall brands to database", brandMapper.toBrandResponseList(brands));
    }
}
