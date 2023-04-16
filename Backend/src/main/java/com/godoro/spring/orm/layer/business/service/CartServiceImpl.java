package com.godoro.spring.orm.layer.business.service;

import com.godoro.spring.orm.layer.business.dto.CartDto;
import com.godoro.spring.orm.layer.business.dto.CartProductDto;
import com.godoro.spring.orm.layer.business.dto.ProductDto;
import com.godoro.spring.orm.layer.data.entity.Cart;
import com.godoro.spring.orm.layer.data.entity.CartProduct;
import com.godoro.spring.orm.layer.data.entity.CartStatus;
import com.godoro.spring.orm.layer.data.entity.Product;
import com.godoro.spring.orm.layer.data.repository.CartProductRepository;
import com.godoro.spring.orm.layer.data.repository.CartRepository;
import com.godoro.spring.orm.layer.data.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @SneakyThrows
    @Override
    public CartDto getCartById(long cartId)  {
        Optional<Cart> optionalCart = cartRepository.findByCartId(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            List<CartProduct> cartProducts = cartProductRepository.findByCart_CartId(cartId);
            List<CartProductDto> cartProductDTOs = new ArrayList<>();
            for (CartProduct cartProduct : cartProducts) {
                ProductDto productDTO = new ProductDto();
                Product product = cartProduct.getProduct();
                productDTO.setProductId(product.getProductId());
                productDTO.setProductName(product.getProductName());
                productDTO.setSalesPrice(product.getSalesPrice());
                CartProductDto cartProductDTO = new CartProductDto();
                cartProductDTO.setCartProductId(cartProduct.getCartProductId());
                cartProductDTO.setProduct(productDTO);
                cartProductDTO.setSalesQuantity(cartProduct.getSalesQuantity());
                cartProductDTOs.add(cartProductDTO);
            }
            CartDto cartDTO = new CartDto();
            cartDTO.setCartId(cart.getCartId());
            cartDTO.setCustomerName(cart.getCustomerName());
            cartDTO.setCardNumber(cart.getCardNumber());
            cartDTO.setCartStatus(cart.getCartStatus());
            cartDTO.setCartProducts(cartProductDTOs);
            return cartDTO;
        } else {
            throw new Exception("Sepet bulunamadı id : " + cartId);
        }
    }
    @SneakyThrows
    @Override
    public void addProductToCart(long cartId, long productId, int quantity) {
        Optional<Cart> optionalCart = cartRepository.findByCartId(cartId);
        Optional<Product> optionalProduct = productRepository.findByProductId(productId);
        if (optionalCart.isPresent() && optionalProduct.isPresent()) {
            Cart cart = optionalCart.get();
            Product product = optionalProduct.get();
            Optional<CartProduct> optionalCartProduct = cartProductRepository.findByCart_CartIdAndProduct_ProductId(cartId, productId);
            CartProduct cartProduct;
            if (optionalCartProduct.isPresent()) {
                cartProduct = optionalCartProduct.get();
                cartProduct.setSalesQuantity(cartProduct.getSalesQuantity() + quantity);
            } else {
                cartProduct = new CartProduct();
                cartProduct.setCart(cart);
                cartProduct.setProduct(product);
                cartProduct.setSalesQuantity(quantity);
            }
            cartProductRepository.save(cartProduct);
        } else {
            throw new Exception("Kart ya da product bulunamadı " + cartId + " or " + productId);
        }
    }
    @SneakyThrows
    @Override
    public void minusProductToCart(long cartId, long productId, int quantity) {
        Optional<Cart> optionalCart = cartRepository.findByCartId(cartId);
        Optional<Product> optionalProduct = productRepository.findByProductId(productId);
        if (optionalCart.isPresent() && optionalProduct.isPresent()) {
            Cart cart = optionalCart.get();
            Product product = optionalProduct.get();
            Optional<CartProduct> optionalCartProduct = cartProductRepository.findByCart_CartIdAndProduct_ProductId(cartId, productId);
            CartProduct cartProduct;
            if (optionalCartProduct.isPresent()) {
                cartProduct = optionalCartProduct.get();
                if(cartProduct.getSalesQuantity()>1){
                    cartProduct.setSalesQuantity(cartProduct.getSalesQuantity() - quantity);
                }
            } else {
                cartProduct = new CartProduct();
                cartProduct.setCart(cart);
                cartProduct.setProduct(product);
                cartProduct.setSalesQuantity(quantity);
            }
            cartProductRepository.save(cartProduct);
        } else {
            throw new Exception("Bu id ile kart ya da ürün yok " + cartId + " ya da " + productId);
        }
    }

    @SneakyThrows
    @Override
    public void removeProductFromCart(long cartId, long productId) {
        Optional<Cart> optionalCart = cartRepository.findByCartId(cartId);
        Optional<Product> optionalProduct = productRepository.findByProductId(productId);
        if (optionalCart.isPresent() && optionalProduct.isPresent()) {
            Optional<CartProduct> optionalCartProduct = cartProductRepository.findByCart_CartIdAndProduct_ProductId(cartId, productId);
            CartProduct cartProduct;
            if (optionalCartProduct.isPresent()) {
                cartProduct = optionalCartProduct.get();
                if(cartProduct.getSalesQuantity()>0){
                    cartProduct.setSalesQuantity(0);
                }
            }
        } else {
            throw new Exception("Bu id ile kart ya da ürün yok " + cartId + " ya da " + productId);
        }
    }

    @SneakyThrows
    @Override
    public void updateCartCardNumber(long cartId, String cardNumber){
        Optional<Cart> optionalCart = cartRepository.findByCartId(cartId);
        if (optionalCart.isPresent()){
            Cart cart = optionalCart.get();
            cart.setCardNumber(cardNumber);
            cartRepository.save(cart);
        }else{
            throw new Exception("Kart Bulunamadı id "+ cartId);
        }
    }


    @SneakyThrows
    @Override
    public void updateCartCustomerName(long cartId, String customerName){
        Optional<Cart> optionalCart = cartRepository.findByCartId(cartId);
        if (optionalCart.isPresent()){
            Cart cart = optionalCart.get();
            cart.setCustomerName(customerName);
            cartRepository.save(cart);
        }else{
            throw new Exception("Kart Bulunamadı id "+ cartId);
        }

    }




    @SneakyThrows
    @Override
    public void checkoutCart(long cartId) {
        Optional<Cart> optionalCart = cartRepository.findByCartId(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setCartStatus(CartStatus.COMPLETED);
            cartRepository.save(cart);
        } else {
            throw new Exception("Sepet bulunamadı id : " + cartId);
        }
    }
}

