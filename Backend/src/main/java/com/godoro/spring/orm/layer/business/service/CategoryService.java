package com.godoro.spring.orm.layer.business.service;

import com.godoro.spring.orm.layer.business.dto.CategoryDto;
import com.godoro.spring.orm.layer.data.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();

}
