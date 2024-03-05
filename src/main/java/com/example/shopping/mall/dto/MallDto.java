package com.example.shopping.mall.dto;

import com.example.shopping.mall.entity.Mall;
import com.example.shopping.mall.entity.enumeration.MallType;
import com.example.shopping.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MallDto {
    private Long id;
    private String title;
    private String introduce;
    private MallType mallType;
    private User user;

    public static MallDto fromEntity(Mall mall) {
        return MallDto.builder()
                .id(mall.getId())
                .title(mall.getTitle())
                .introduce(mall.getIntroduce())
                .mallType(mall.getMallType())
                .user(mall.getUser())
                .build();
    }
}
