package com.godoro.spring.orm.layer.business.dto;

import lombok.Data;

@Data
public class ProductDto {
    private long productId;
    private String productName;
    private double salesPrice;
    private String imagePath;
    private CategoryDto category;

}
