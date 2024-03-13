package com.example.shopping.domain.mall.repository;

import com.example.shopping.domain.mall.entity.MallProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MallProductRepository extends JpaRepository<MallProduct, Long> {
    List<MallProduct> findByPriceBetweenOrNameIgnoreCase(int minPrice, int maxPrice, String name);

//    List<MallProduct> findByName();
}
