package com.example.shopping.usedProduct.repository;

import com.example.shopping.usedProduct.entity.UsedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedProductRepository extends JpaRepository<UsedProduct, Long> {
}

