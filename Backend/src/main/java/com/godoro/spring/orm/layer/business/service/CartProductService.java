package com.godoro.spring.orm.layer.business.service;

import com.godoro.spring.orm.layer.business.dto.CartProductDto;

import java.util.List;

public interface CartProductService {
    List<CartProductDto> getCartProductsByCartId(long cartId);
}
