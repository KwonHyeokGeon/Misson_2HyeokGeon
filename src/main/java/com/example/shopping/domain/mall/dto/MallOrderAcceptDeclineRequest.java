package com.example.shopping.domain.mall.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MallOrderAcceptDeclineRequest {
    @NotNull
    private Long mallOrderId;
}
