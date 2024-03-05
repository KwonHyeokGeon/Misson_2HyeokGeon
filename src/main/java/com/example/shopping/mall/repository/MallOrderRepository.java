package com.example.shopping.mall.repository;

import com.example.shopping.mall.entity.MallOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MallOrderRepository extends JpaRepository<MallOrder, Long> {
}
