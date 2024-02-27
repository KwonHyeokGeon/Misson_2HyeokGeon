package com.example.shopping.repository;

import com.example.shopping.entity.UsedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedProductRepository extends JpaRepository<UsedProduct, Long> {
}

