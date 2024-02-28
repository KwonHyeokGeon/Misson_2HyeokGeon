package com.example.shopping.dto;

import com.example.shopping.entity.User;
import com.example.shopping.entity.enumeration.UserAuth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDto {
//    private Long id;
    private String userId;
    private String password;
//    private String name;
//    private String nickname;
//    private String age;
//    private String phone;
//    private byte[] profileImage;
//    private UserAuth auth;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
//                .id(user.getId())
                .userId(user.getUserId())
                .password(user.getPassword())
//                .name(user.getName())
//                .nickname(user.getNickname())
//                .age(user.getAge())
//                .phone(user.getPhone())
//                .profileImage(user.getProfileImage())
//                .auth(user.getAuth())
                .build();
    }

}
