package com.example.shopping.domain.user.repository;

import com.example.shopping.domain.user.entity.BusinessRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<BusinessRegistration, Long> {
}
