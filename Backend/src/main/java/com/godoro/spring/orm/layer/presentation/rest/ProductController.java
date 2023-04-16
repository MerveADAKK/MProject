package com.godoro.spring.orm.layer.presentation.rest;
import com.godoro.spring.orm.layer.business.dto.ProductDto;
import com.godoro.spring.orm.layer.business.service.ProductService;
import com.godoro.spring.orm.layer.business.service.ProductServiceImpl;
import com.godoro.spring.orm.layer.data.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    private ProductServiceImpl productService;
    ProductController(ProductServiceImpl productService){
        this.productService=productService;
    }
    @GetMapping("/{id}")
    public ProductDto getAccount(@PathVariable("id") long productId) {

        return productService.find(productId);
    }
    @GetMapping("")
    public List<ProductDto> findByCategoryIdOrderByProductNameAsc(@RequestParam long category_id) {
        return productService.findByCategoryIdOrderByProductNameAsc(category_id);
    }

}
