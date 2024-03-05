package com.example.shopping.user.repository;

import com.example.shopping.user.entity.BusinessRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<BusinessRegistration, Long> {
}
