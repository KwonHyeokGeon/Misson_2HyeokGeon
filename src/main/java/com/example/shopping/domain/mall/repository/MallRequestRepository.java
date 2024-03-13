package com.example.shopping.domain.mall.repository;

import com.example.shopping.domain.mall.entity.MallRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MallRequestRepository extends JpaRepository<MallRequest, Long> {
}
