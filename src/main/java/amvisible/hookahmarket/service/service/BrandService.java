package amvisible.hookahmarket.service.service;

import amvisible.hookahmarket.data.model.Brand;
import amvisible.hookahmarket.service.model.BrandServiceModel;

import java.util.List;

public interface BrandService {

    List<Brand> getAll();

    void addNewBrand(BrandServiceModel brandServiceModel);
}
