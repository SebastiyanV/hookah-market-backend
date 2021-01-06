package amvisible.hookahmarket.service.service;

import amvisible.hookahmarket.data.model.Category;
import amvisible.hookahmarket.service.model.CategoryServiceModel;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    void addNewCategory(CategoryServiceModel categoryServiceModel);
}
