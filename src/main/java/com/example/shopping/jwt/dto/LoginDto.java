package com.example.shopping.jwt.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String userId;
    private String password;

    private String userValue;
}


