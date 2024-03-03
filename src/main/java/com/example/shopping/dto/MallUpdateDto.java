package com.example.shopping.dto;

import com.example.shopping.entity.enumeration.MallType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MallUpdateDto {
    private String title;
    private String introduce;
    private MallType mallType;
}
