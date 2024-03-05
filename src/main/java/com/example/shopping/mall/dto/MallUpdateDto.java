package com.example.shopping.mall.dto;

import com.example.shopping.mall.entity.enumeration.MallType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MallUpdateDto {
    private String title;
    private String introduce;
    private MallType malltype;
}
