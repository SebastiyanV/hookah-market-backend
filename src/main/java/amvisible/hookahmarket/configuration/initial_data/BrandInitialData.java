package amvisible.hookahmarket.configuration.initial_data;

import amvisible.hookahmarket.data.enumerate.BrandEnum;
import amvisible.hookahmarket.data.model.Brand;
import amvisible.hookahmarket.service.model.BrandServiceModel;
import amvisible.hookahmarket.service.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BrandInitialData {

    private final BrandService brandService;

    @Autowired
    public BrandInitialData(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostConstruct
    public void init() {
        List<String> brands = this.brandService.getAll()
                .stream()
                .map(Brand::getName)
                .collect(Collectors.toList());

        Arrays.stream(BrandEnum.values())
                .forEach(brand -> {
                    if (!brands.contains(brand.getName())) {
                        this.brandService.addNewBrand(new BrandServiceModel(brand.getName()));
                    }
                });
    }
}
