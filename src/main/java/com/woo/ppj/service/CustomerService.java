package com.woo.ppj.service;

import com.woo.ppj.model.Qna;
import com.woo.ppj.model.User;
import com.woo.ppj.repository.CustomerRepository;
import com.woo.ppj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Qna save(String username, Qna qna) {
        User user = userRepository.findByUsername(username);
        qna.setUser(user);
        return customerRepository.save(qna);
    }
}
