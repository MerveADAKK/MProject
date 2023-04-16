package com.godoro.spring.orm.layer.presentation.rest;

import com.godoro.spring.orm.layer.business.dto.CartProductDto;
import com.godoro.spring.orm.layer.business.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cart-product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartProductController {

    @Autowired
    private CartProductService cartProductService;

    @GetMapping("/list/{cartId}")
    public List<CartProductDto> getCartProductsByCartId(@PathVariable int cartId) {
        return cartProductService.getCartProductsByCartId(cartId);
    }
}