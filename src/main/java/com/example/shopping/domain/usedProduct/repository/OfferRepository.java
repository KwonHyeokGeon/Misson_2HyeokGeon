package com.example.shopping.domain.usedProduct.repository;

import com.example.shopping.domain.usedProduct.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
