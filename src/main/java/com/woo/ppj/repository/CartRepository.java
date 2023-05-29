package com.woo.ppj.repository;

import com.woo.ppj.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUser_id(Long user_id); // 장바구니들 찾기

    Cart findByUser_idAndName(Long user_id, String name); //

    Long countByUser_id(Long user_id);
}
