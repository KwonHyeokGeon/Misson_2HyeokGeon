package com.example.shopping.mall.dto;

import com.example.shopping.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MallRequestDto {
    private Long id;
    private String title;
    private String explanation;

    private User user;
}