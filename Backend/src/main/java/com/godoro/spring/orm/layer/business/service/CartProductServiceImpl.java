package com.godoro.spring.orm.layer.business.service;
import com.godoro.spring.orm.layer.business.dto.CartProductDto;
import com.godoro.spring.orm.layer.business.dto.ProductDto;
import com.godoro.spring.orm.layer.data.entity.CartProduct;
import com.godoro.spring.orm.layer.data.entity.Product;
import com.godoro.spring.orm.layer.data.repository.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartProductServiceImpl implements CartProductService {

    @Autowired
    private CartProductRepository cartProductRepository;
    @Override
    public List<CartProductDto> getCartProductsByCartId(long cartId) {
        List<CartProduct> cartProducts = cartProductRepository.findByCart_CartId(cartId);
        List<CartProductDto> cartProductDtos = new ArrayList<>();
        for (CartProduct cartProduct : cartProducts) {
            ProductDto productDto = new ProductDto();
            Product product = cartProduct.getProduct();
            productDto.setProductId(product.getProductId());
            productDto.setProductName(product.getProductName());
            CartProductDto cartProductDto = new CartProductDto();
            cartProductDto.setCartProductId(cartProduct.getCartProductId());
            cartProductDto.setCartId(cartProduct.getCart().getCartId());
            cartProductDto.setProduct(productDto);
            cartProductDto.setSalesQuantity(cartProduct.getSalesQuantity());
            cartProductDtos.add(cartProductDto);
        }
        return cartProductDtos;
    }
}

