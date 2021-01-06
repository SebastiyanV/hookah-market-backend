package amvisible.hookahmarket.service.service.impl;

import amvisible.hookahmarket.data.model.Category;
import amvisible.hookahmarket.data.repository.CategoryRepository;
import amvisible.hookahmarket.service.model.CategoryServiceModel;
import amvisible.hookahmarket.service.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public void addNewCategory(CategoryServiceModel categoryServiceModel) {
        Category category = this.modelMapper.map(categoryServiceModel, Category.class);
        this.categoryRepository.saveAndFlush(category);
    }
}
