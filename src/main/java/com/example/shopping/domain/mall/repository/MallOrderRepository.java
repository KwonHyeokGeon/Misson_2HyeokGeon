package com.example.shopping.domain.mall.repository;

import com.example.shopping.domain.mall.entity.MallOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MallOrderRepository extends JpaRepository<MallOrder, Long> {
}
