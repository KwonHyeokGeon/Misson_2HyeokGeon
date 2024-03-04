package com.example.shopping.repository;

import com.example.shopping.entity.MallRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MallRejectionRepository extends JpaRepository<MallRequest, Long> {
}
