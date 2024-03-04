package com.example.shopping.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MallOrderRequest {
    @NotNull
    private Long mallProductId;
    @NotNull
    private Integer count;
}
