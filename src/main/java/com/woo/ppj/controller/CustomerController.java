package com.woo.ppj.controller;

import com.woo.ppj.model.Qna;
import com.woo.ppj.repository.QnaRepository;
import com.woo.ppj.service.QnaService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private QnaRepository qnaRepository;

    @Autowired
    private QnaService qnaService;

    @GetMapping("/qna")
    public String qna(Model model,@PageableDefault() Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String searchText) {
        Page qnaBoards = qnaRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int now = qnaBoards.getPageable().getPageNumber() + 1;
        int startPage = Math.max(1, now - 3);
        int endPage = Math.min(qnaBoards.getTotalPages(), now + 3);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("qnaBoards", qnaBoards);
        return "/customer/qna";
    }

    @GetMapping("/qnaForm")
    public String qnaForm(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("qnaBoards", new Qna());
        } else {
            Qna qnaBoards = qnaRepository.findById(id).orElse(null);
            model.addAttribute("qnaBoards", qnaBoards);
        }
        return "/customer/qnaForm";
    }

    @PostMapping("/qnaForm")
    public String qnaForm(Qna qna, Authentication authentication) {
        String username = authentication.getName();

        qnaService.save(username, qna);
        return "redirect:/customer/qna";
    }

    @GetMapping("/qnaDetail")
    public String qnaDetail(Model model, Long id , Authentication authentication) {
        Qna qnaBoards = qnaRepository.findById(id).orElse(null);
        Boolean correctId = qnaBoards.getUser().getUsername().equals(authentication.getName());
        model.addAttribute("correctId", correctId);
        model.addAttribute("qnaBoards", qnaBoards);
        return "/customer/qnaDetail";
    }
}
