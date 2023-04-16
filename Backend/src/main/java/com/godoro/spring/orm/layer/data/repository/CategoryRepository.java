package com.godoro.spring.orm.layer.data.repository;

import com.godoro.spring.orm.layer.data.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}