package com.example.shopping.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    private String name;
    private String nickname;
    private String age;
    private String phone;
    private byte[] profileImage;
    private String businessNum;
}
