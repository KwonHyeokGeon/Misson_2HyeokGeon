package com.example.shopping.user.repository;

import com.example.shopping.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    boolean existsByUserId(String userId);
}
