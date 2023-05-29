package com.woo.ppj.controller;

import com.woo.ppj.model.Cart;
import com.woo.ppj.model.Goods;
import com.woo.ppj.repository.CartRepository;
import com.woo.ppj.repository.GoodsRepository;
import com.woo.ppj.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Access;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private CartService cartService;

    @GetMapping("/detail")
    String detail(Model model, Long id, Authentication authentication) {
        Goods goods = goodsRepository.findById(id).orElse(null);
        List<Goods> goodsRelList = goodsRepository.findByCategory(goods.getCategory());
        goodsRelList.remove(goods);
        Cart cart = cartService.cartCheck(authentication.getName(), goods.getName());

        model.addAttribute("goods", goods);
        model.addAttribute("goodsRelList", goodsRelList);
        model.addAttribute("cart", cart);
        return "/goods/detail";
    }

    @GetMapping("/cart")
    String cart(Model model, Authentication authentication) {
        List<Cart> cartList = cartService.cartList(authentication.getName());
        Long cartCount = 0l;
        if(authentication != null) {
            cartCount = cartService.cartCount(authentication.getName());
        }
        model.addAttribute("cartCount", cartCount);
        model.addAttribute("cartList", cartList);
        return "/goods/cart";
    }
}
