package com.example.shopping.mall.repository;

import com.example.shopping.mall.entity.MallProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MallProductRepository extends JpaRepository<MallProduct, Long> {
    List<MallProduct> findByPriceBetweenOrNameIgnoreCase(int minPrice, int maxPrice, String name);

//    List<MallProduct> findByName();
}
