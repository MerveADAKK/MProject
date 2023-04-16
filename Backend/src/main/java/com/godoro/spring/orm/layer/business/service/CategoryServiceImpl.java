package com.godoro.spring.orm.layer.business.service;

import com.godoro.spring.orm.layer.business.dto.CategoryDto;
import com.godoro.spring.orm.layer.data.entity.Category;
import com.godoro.spring.orm.layer.data.repository.CategoryRepository;
import com.godoro.spring.orm.layer.utilities.mappers.ModelMapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapperServiceImpl modelMapperService;

    @Autowired
    public  CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapperServiceImpl modelMapperService){
        this.categoryRepository=categoryRepository;
        this.modelMapperService=modelMapperService;
    }
    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        List<CategoryDto> brandsResponse = categories.stream().map(
                brand -> this.modelMapperService.forResponse().map(brand,CategoryDto.class)
        ).collect(Collectors.toList());
        return brandsResponse;
    }

    public CategoryDto findById(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            return modelMapperService.forResponse().map(category, CategoryDto.class);
        }
        return null;
    }

}
