package com.woo.ppj.controller;

import com.woo.ppj.repository.QnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerApiController {

    @Autowired
    private QnaRepository qnaRepository;

    @DeleteMapping("/qna/{id}")
    void deleteQna(@PathVariable Long id) {
        qnaRepository.deleteById(id);
    }
}
