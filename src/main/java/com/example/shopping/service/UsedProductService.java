package com.example.shopping.service;

import com.example.shopping.dto.UsedProductDto;
import com.example.shopping.entity.UsedProduct;
import com.example.shopping.entity.enumeration.UsedProductStatus;
import com.example.shopping.repository.UsedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsedProductService {
    private final UsedProductRepository usedProductRepository;

    public void register(UsedProductDto dto) {
        UsedProduct usedProduct = UsedProduct.builder()
                .title(dto.getTitle())
                .explanation(dto.getExplanation())
                .minPrice(dto.getMinPrice())
                .thumbnail(dto.getThumbnail())
                .status(UsedProductStatus.SALE)
                .user(dto.getUser())
                .build();
        usedProductRepository.save(usedProduct);
    }
}
