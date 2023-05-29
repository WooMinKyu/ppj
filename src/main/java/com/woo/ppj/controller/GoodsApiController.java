package com.woo.ppj.controller;

import com.woo.ppj.model.Cart;
import com.woo.ppj.model.Goods;
import com.woo.ppj.model.User;
import com.woo.ppj.repository.CartRepository;
import com.woo.ppj.repository.GoodsRepository;
import com.woo.ppj.repository.UserRepository;
import com.woo.ppj.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class GoodsApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private GoodsRepository goodsRepository;

    @PostMapping("/cart")
    Cart addCart(@RequestBody Cart cart, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        cart.setUser(user);

        return cartRepository.save(cart);
    }

    @DeleteMapping("/cart/{id}")
    void deleteQna(@PathVariable Long id) {
        cartRepository.deleteById(id);
    }

}
