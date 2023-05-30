package com.woo.ppj.controller;

import com.woo.ppj.model.User;
import com.woo.ppj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @DeleteMapping("/leave/{id}/{password}")
    void deleteQna(@PathVariable Long id, @PathVariable String password) throws Exception {
        User user = userRepository.findById(id).orElse(null);

        if(passwordEncoder.matches(password, user.getPassword())) {
            userRepository.deleteById(id);
        } else {
            throw new Exception("비밀번호가 일치하지 않음!");
        }
    }
}
