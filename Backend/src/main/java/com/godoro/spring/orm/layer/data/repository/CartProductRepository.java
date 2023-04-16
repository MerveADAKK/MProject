package com.godoro.spring.orm.layer.data.repository;

import com.godoro.spring.orm.layer.data.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    List<CartProduct> findByCart_CartId(long cardId);
    Optional <CartProduct> findByCart_CartIdAndProduct_ProductId(long cardId, long productId);


}
