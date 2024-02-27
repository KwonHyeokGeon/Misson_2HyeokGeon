package com.example.shopping.repository;

import com.example.shopping.entity.BusinessRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<BusinessRegistration, Long> {
}
