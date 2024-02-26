package com.example.shopping.service;

import com.example.shopping.dto.UserDto;
import com.example.shopping.dto.UserUpdateDto;
import com.example.shopping.entity.User;
import com.example.shopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public void signUp(UserDto userDto) {
        User build = User.builder()
                .userId(userDto.getUserId())
                .password(userDto.getPassword())
                .age(userDto.getAge())
                .phone(userDto.getPhone())
                .name(userDto.getName())
                .auth(userDto.getAuth())
                .nickname(userDto.getNickname())
                .profileImage(userDto.getProfileImage())
                .build();
        userRepository.save(build);
    }

    @Transactional
    public void update(UserUpdateDto userUpdateDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        user.setName(userUpdateDto.getName());
        user.setAge(userUpdateDto.getAge());
        user.setNickname(userUpdateDto.getNickname());
        user.setPhone(userUpdateDto.getPhone());
        user.setProfileImage(userUpdateDto.getProfileImage());
    }

    public void login() {

    }


}
