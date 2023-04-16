package com.godoro.spring.orm.layer.business.service;
import com.godoro.spring.orm.layer.business.dto.CartDto;

public interface CartService {
    CartDto getCartById(long cartId);

    void addProductToCart(long cartId, long productId, int quantity);

    void minusProductToCart(long cartId, long productId, int quantity);

    void removeProductFromCart(long cartId, long productId);

    void checkoutCart(long cartId);

    void updateCartCardNumber(long cartId, String cardNumber);
    void updateCartCustomerName(long cartId, String customerName);


}


