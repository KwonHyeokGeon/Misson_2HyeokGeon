package com.example.shopping.dto;

import com.example.shopping.entity.UsedProduct;
import com.example.shopping.entity.enumeration.UsedProductStatus;
import com.example.shopping.entity.User;
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

    public static UsedProductDto fromEntity(UsedProduct product) {
        return UsedProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .explanation(product.getExplanation())
                .thumbnail(product.getThumbnail())
                .minPrice(product.getMinPrice())
                .status(product.getStatus())
                .user(product.getUser())
                .build();
    }
}
