package com.example.shopping.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferCreateRequest {
    @NotNull
    private Long usedProductId;
    @NotNull
    private String title;
    @NotNull
    private String content;
}
