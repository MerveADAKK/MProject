package com.godoro.spring.orm.layer.business.dto;
import com.godoro.spring.orm.layer.data.entity.CartStatus;
import lombok.Data;
import java.util.List;
@Data
public class CartDto {
    private long cartId;
    private String customerName;
    private String cardNumber;
    private CartStatus cartStatus = CartStatus.NEW;;
    private List<CartProductDto> cartProducts;
}
