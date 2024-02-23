package com.example.shopping.dto;

import com.example.shopping.entity.Mall;
import com.example.shopping.entity.MallProduct;
import com.example.shopping.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MallProductDto {
    private Long id;
    private String name;
    private byte[] image;
    private String explanation;
    private int price;
    private int stock;
    private User user;
    private Mall mall;

    public static MallProductDto fromEntity(MallProduct product) {
        return MallProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .explanation(product.getExplanation())
                .price(product.getPrice())
                .stock(product.getStock())
                .user(product.getUser())
                .mall(product.getMall())
                .build();
    }
}
