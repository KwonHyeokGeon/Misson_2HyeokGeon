package com.example.shopping.usedProduct.dto;

import com.example.shopping.user.entity.User;
import com.example.shopping.usedProduct.entity.enumeration.UsedProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UsedProductDto {
    private Long id;
    private String title;
    private String explanation;
    private byte[] thumbnail;
    private int minPrice;
    private UsedProductStatus status;
    private User user;

}
