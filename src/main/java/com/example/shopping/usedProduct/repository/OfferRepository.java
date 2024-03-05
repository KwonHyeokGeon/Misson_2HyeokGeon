package com.example.shopping.usedProduct.repository;

import com.example.shopping.usedProduct.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}