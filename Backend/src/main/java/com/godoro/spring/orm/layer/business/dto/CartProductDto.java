package com.godoro.spring.orm.layer.business.dto;
import lombok.Data;
@Data
public class CartProductDto {
    private long cartProductId;
    private ProductDto product;
    private long cartId;
    private int salesQuantity;
}
