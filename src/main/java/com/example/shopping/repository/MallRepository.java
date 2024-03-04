package com.example.shopping.repository;

import com.example.shopping.entity.Mall;
import com.example.shopping.entity.enumeration.MallType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MallRepository extends JpaRepository<Mall, Long> {
    Mall findByUserId(Long userId);

    void deleteByUserId(Long userId);

    List<Mall> findByMallTypeOrTitle(MallType mallType, String title);

//    List<Mall> findByTitle(String title);
}
