package com.woo.ppj.controller;

import com.woo.ppj.model.User;
import com.woo.ppj.repository.UserRepository;
import com.woo.ppj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "/account/login";
    }

    @GetMapping("/register")
    public String register(){

        return "/account/register";
    }

    @PostMapping("/register")
    public String register(User user){
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String myPage(){
        return "/account/myPage";
    }

    @GetMapping("/modify")
    public String modify(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "/account/modify";
    }

    @PostMapping("/modify")
    public String modify(User user) {
        userService.save(user);
        return "redirect:/account/myPage";
    }

    @GetMapping("/leave")
    public String delete(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "/account/leave";
    }
}
