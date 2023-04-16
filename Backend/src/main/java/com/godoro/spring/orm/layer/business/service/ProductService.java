package com.godoro.spring.orm.layer.business.service;

import com.godoro.spring.orm.layer.business.dto.ProductDto;
import com.godoro.spring.orm.layer.data.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDto find(long productId);

}
