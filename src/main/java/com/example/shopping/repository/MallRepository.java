package com.example.shopping.repository;

import com.example.shopping.entity.Mall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MallRepository extends JpaRepository<Mall, Long> {
}
