package com.godoro.spring.orm.layer.presentation.rest;

import com.godoro.spring.orm.layer.business.dto.CategoryDto;
import com.godoro.spring.orm.layer.business.service.CategoryService;
import com.godoro.spring.orm.layer.business.service.CategoryServiceImpl;
import com.godoro.spring.orm.layer.data.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
    private CategoryServiceImpl categoryService;
    CategoryController(CategoryServiceImpl categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping()
    public List<CategoryDto> getAll(){
        return categoryService.getAll();
    }
    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable("id") int id){
        return categoryService.findById(id);
    }
}

