package com.example.shopping.repository;

import com.example.shopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    boolean existsByUserId(String userId);
}
