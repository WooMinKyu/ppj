package com.woo.ppj.repository;

import com.woo.ppj.model.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Qna, Long> {
}
