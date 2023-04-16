package com.godoro.spring.orm.layer.presentation.rest;
import com.godoro.spring.orm.layer.business.dto.CartDto;
import com.godoro.spring.orm.layer.business.service.CartService;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/get/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable("id") long id) {
        CartDto cartDto = cartService.getCartById(id);
        return ResponseEntity.ok(cartDto);
    }

    @PostMapping("/update/{cartid}/{cardnumber}")
    public ResponseEntity<String> updateCartCardNumber(@PathVariable("cartid") long cartid, @PathVariable("cardnumber") String cardnumber){
        cartService.updateCartCardNumber(cartid,cardnumber);
        return ResponseEntity.ok("Kart Numarası Güncellendi");
    }
    @PostMapping("/updateName/{cartid}/{customername}")
    public ResponseEntity<String> updateCartCustomerName(@PathVariable("cartid") long cartid, @PathVariable("customername") String customername){
        cartService.updateCartCustomerName(cartid,customername);
        return ResponseEntity.ok("İsminiz Güncellendi");
    }

    @PostMapping("/add/{cartid}/{productid}")
    public ResponseEntity<String> addProductToCart(@PathVariable("cartid") long cartid, @PathVariable("productid") long productid, @RequestParam int quantity) {
        cartService.addProductToCart(cartid, productid, quantity);
        return ResponseEntity.ok("Ürün sepete eklendi.");
    }

    @PostMapping("/minus/{cartid}/{productid}")
    public ResponseEntity<String> minusProductToCart(@PathVariable("cartid") long cartid, @PathVariable("productid") long productid, @RequestParam int quantity) {
        cartService.minusProductToCart(cartid, productid, quantity);
        return ResponseEntity.ok("Ürün sepete eklendi.");
    }

    @PostMapping("/remove/{cartid}/{productid}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable("cartid") long cartid, @PathVariable("productid") long productid) {
        cartService.removeProductFromCart(cartid, productid);
        return ResponseEntity.ok("Ürün sepetten kaldırıldı.");
    }

    @PostMapping("/checkout/{id}")
    public ResponseEntity<String> checkoutCart(@PathVariable("id") long id) {
        cartService.checkoutCart(id);
        return ResponseEntity.ok("Sepet onaylandı.");
    }
}
