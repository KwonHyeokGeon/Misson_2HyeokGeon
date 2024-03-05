package com.example.shopping.mall.repository;

import com.example.shopping.mall.entity.MallRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MallRejectionRepository extends JpaRepository<MallRequest, Long> {
}
