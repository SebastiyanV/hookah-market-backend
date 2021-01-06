package amvisible.hookahmarket.configuration.initial_data;

import amvisible.hookahmarket.data.enumerate.CategoryEnum;
import amvisible.hookahmarket.data.model.Category;
import amvisible.hookahmarket.service.model.CategoryServiceModel;
import amvisible.hookahmarket.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryInitialData {

    private final CategoryService categoryService;

    @Autowired
    public CategoryInitialData(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostConstruct
    public void init() {
        List<String> categories = this.categoryService.getAll()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());

        Arrays.stream(CategoryEnum.values())
                .forEach(category -> {
                    if (!categories.contains(category.getName())) {
                        this.categoryService.addNewCategory(new CategoryServiceModel(category.getName()));
                    }
                });
    }
}
