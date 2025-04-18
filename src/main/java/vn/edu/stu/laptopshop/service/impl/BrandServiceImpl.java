package vn.edu.stu.laptopshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.stu.laptopshop.controller.request.brand.BrandCreateRequest;
import vn.edu.stu.laptopshop.controller.request.brand.BrandUpdateRequest;
import vn.edu.stu.laptopshop.exception.InvalidDataException;
import vn.edu.stu.laptopshop.exception.ResourceNotFoundException;
import vn.edu.stu.laptopshop.model.BrandEntity;
import vn.edu.stu.laptopshop.repository.BrandRepository;
import vn.edu.stu.laptopshop.service.BrandService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public BrandEntity addBrand(BrandCreateRequest request) {
        Optional<BrandEntity> brandEntity = brandRepository.findByName(request.getName());
        if (brandEntity.isPresent()) {
            throw new InvalidDataException("Brand name already exist");
        }
        BrandEntity brand = BrandEntity.builder()
                .name(request.getName())
                .build();

        brandRepository.save(brand);

        return brand;
    }

    @Override
    public void updateBrand(BrandUpdateRequest request) {
        BrandEntity brandEntity = getBrandById(request.getId());
        if (brandEntity != null) {
            brandEntity.setName(request.getName());
            brandRepository.save(brandEntity);
        }
    }

    @Override
    public void deleteBrand(Long id) {
        BrandEntity brandEntity = getBrandById(id);
        if (brandEntity != null && brandEntity.getProducts().isEmpty()) {
            brandRepository.delete(brandEntity);
        }else {
            throw new InvalidDataException("Cannot delete brand because it contains product table constraints");
        }
    }

    @Override
    public BrandEntity getBrandById(Long id) {
        return brandRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Brand id" + id + " not found"));
    }

    @Override
    public List<BrandEntity> getAllBrands() {
        return brandRepository.findAll();
    }
}
