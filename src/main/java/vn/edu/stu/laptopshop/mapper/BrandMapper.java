package vn.edu.stu.laptopshop.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.laptopshop.controller.response.brand.BrandResponse;
import vn.edu.stu.laptopshop.model.BrandEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BrandMapper {
    private final ModelMapper modelMapper;

    public BrandResponse toBrandResponse(BrandEntity brandEntity) {
        return modelMapper.map(brandEntity, BrandResponse.class);
    }

    public List<BrandResponse> toBrandResponseList(List<BrandEntity> brandEntities) {
           return brandEntities.stream().map(
                   brandEntity -> modelMapper.map(brandEntity, BrandResponse.class))
                   .toList();
    }
}
