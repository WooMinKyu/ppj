package com.woo.ppj.controller;

import com.woo.ppj.model.Shop;
import com.woo.ppj.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping
    public String index(Model model, @RequestParam(required = false) String searchText) {
        List<Shop> goods = null;
        if(searchText == null){
            goods = shopRepository.findAll();
        } else{
            goods = shopRepository.findByNameContaining(searchText);
        }
        model.addAttribute("goods", goods);
        return "index";
    }
}
