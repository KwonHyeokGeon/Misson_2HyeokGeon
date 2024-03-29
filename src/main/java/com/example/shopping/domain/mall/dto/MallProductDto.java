package com.example.shopping.domain.mall.dto;

import com.example.shopping.domain.mall.entity.Mall;
import com.example.shopping.domain.mall.entity.MallProduct;
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
    private Mall mall;


    public static MallProductDto fromEntity(MallProduct product) {
        return MallProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .explanation(product.getExplanation())
                .price(product.getPrice())
                .stock(product.getStock())
                .mall(product.getMall())
                .build();
    }
}
