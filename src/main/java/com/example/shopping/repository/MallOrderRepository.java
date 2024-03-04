package com.example.shopping.repository;

import com.example.shopping.entity.MallOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MallOrderRepository extends JpaRepository<MallOrder, Long> {
}
