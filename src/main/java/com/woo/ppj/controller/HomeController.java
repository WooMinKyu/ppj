package com.woo.ppj.controller;

import com.woo.ppj.model.Goods;
import com.woo.ppj.repository.CartRepository;
import com.woo.ppj.repository.GoodsRepository;
import com.woo.ppj.service.CartService;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private GoodsRepository shopRepository;

    @Autowired
    private CartService cartService;

    @GetMapping
    public String index(Model model, @RequestParam(required = false) String searchText) {
        List<Goods> goodsList = null;
        if(searchText == null){
            goodsList = shopRepository.findAll();
        } else{
            goodsList = shopRepository.findByNameContaining(searchText);
        }
        model.addAttribute("goodsList", goodsList);
        return "index";
    }
}
