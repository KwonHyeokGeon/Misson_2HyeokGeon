package com.example.shopping.domain.usedProduct.repository;

import com.example.shopping.domain.usedProduct.entity.UsedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedProductRepository extends JpaRepository<UsedProduct, Long> {
}

