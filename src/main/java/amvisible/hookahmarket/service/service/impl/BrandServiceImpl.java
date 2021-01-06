package amvisible.hookahmarket.service.service.impl;

import amvisible.hookahmarket.data.model.Brand;
import amvisible.hookahmarket.data.repository.BrandRepository;
import amvisible.hookahmarket.service.model.BrandServiceModel;
import amvisible.hookahmarket.service.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Brand> getAll() {
        return this.brandRepository.findAll();
    }

    @Override
    public void addNewBrand(BrandServiceModel brandServiceModel) {
        Brand brand = this.modelMapper.map(brandServiceModel, Brand.class);
        this.brandRepository.saveAndFlush(brand);
    }
}
