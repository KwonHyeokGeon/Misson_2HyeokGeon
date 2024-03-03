package com.example.shopping.repository;

import com.example.shopping.entity.MallRegistration;
import com.example.shopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MallRegisterRepository extends JpaRepository<MallRegistration, Long> {
}
