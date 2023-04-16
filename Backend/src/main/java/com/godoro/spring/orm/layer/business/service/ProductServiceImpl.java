package com.godoro.spring.orm.layer.business.service;

import com.godoro.spring.orm.layer.business.dto.ProductDto;
import com.godoro.spring.orm.layer.data.entity.Product;
import com.godoro.spring.orm.layer.data.repository.ProductRepository;
import com.godoro.spring.orm.layer.utilities.mappers.ModelMapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapperServiceImpl modelMapperService;


    @Override
    public ProductDto find(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductDto productDto = modelMapperService.forResponse().map(product, ProductDto.class);

            return productDto;
        }
        return null;
    }
    public List<ProductDto> findByCategoryIdOrderByProductNameAsc(long categoryId) {
        List<Product> productList = productRepository.findAllByCategoryCategoryIdOrderByProductNameAsc(categoryId);
        return productList.stream()
                .map(product -> modelMapperService.forResponse().map(product, ProductDto.class))
                .collect(Collectors.toList());
    }
}
