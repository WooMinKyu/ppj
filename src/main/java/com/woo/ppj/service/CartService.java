package com.woo.ppj.service;

import com.woo.ppj.model.Cart;
import com.woo.ppj.model.User;
import com.woo.ppj.repository.CartRepository;
import com.woo.ppj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    public  Cart cartCheck(String username, String goodsName){
        User user = userRepository.findByUsername(username);
        Long user_id = user.getId();
        Cart cart = cartRepository.findByUser_idAndName(user_id, goodsName);
        return cart;
    }

    public List<Cart> cartList(String username) {
        User user = userRepository.findByUsername(username);
        Long user_id = user.getId();
        List<Cart> cartList = cartRepository.findByUser_id(user_id);
        return cartList;
    }

    public Long cartCount(String username) {
        User user = userRepository.findByUsername(username);
        Long user_id = user.getId();
        Long count = cartRepository.countByUser_id(user_id);
        if(count == null){
            count= 0l;
        }
        return count;
    }
}
