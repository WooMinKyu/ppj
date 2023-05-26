package com.woo.ppj.repository;

import com.woo.ppj.model.Qna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {
    Page<Qna> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
