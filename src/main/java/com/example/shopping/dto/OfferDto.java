package com.example.shopping.dto;

import com.example.shopping.entity.Offer;
import com.example.shopping.entity.enumeration.OfferStatus;
import com.example.shopping.entity.UsedProduct;
import com.example.shopping.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OfferDto {
    private Long id;
    private String title;
    private String content;
    private OfferStatus status;
    private User user;
    private UsedProduct usedProduct;

    public static OfferDto fromEntity(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .title(offer.getTitle())
                .content(offer.getContent())
                .status(offer.getStatus())
                .user(offer.getUser())
                .usedProduct(offer.getUsedProduct())
                .build();
    }
}
