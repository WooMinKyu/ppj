package com.woo.ppj.repository;

import com.woo.ppj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
