package com.godoro.spring.orm.layer.data.repository;
import com.godoro.spring.orm.layer.data.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findByProductId(long productId);
    List<Product> findAllByCategoryCategoryIdOrderByProductNameAsc(long categoryId);

}