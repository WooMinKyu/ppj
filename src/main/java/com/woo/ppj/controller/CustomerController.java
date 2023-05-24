package com.woo.ppj.controller;

import com.woo.ppj.model.Qna;
import com.woo.ppj.repository.CustomerRepository;
import com.woo.ppj.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/qna")
    public String qna(Model model, Pageable pageable) {
        Page qnaBoards = customerRepository.findAll(pageable);
        model.addAttribute("qnaBoards", qnaBoards);
        return "/customer/qna";
    }

    @GetMapping("/qnaForm")
    public String qnaForm() {
        return "/customer/qnaForm";
    }

    @PostMapping("/qnaForm")
    public String qnaForm(Qna qna, Authentication authentication) {
        String username = authentication.getName();

        customerService.save(username, qna);
        return "redirect:/customer/qna";
    }
}
