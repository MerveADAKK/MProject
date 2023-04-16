package com.godoro.spring.orm.layer.data.repository;

import com.godoro.spring.orm.layer.data.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    Optional<Cart> findByCartId(long cartId);

}

