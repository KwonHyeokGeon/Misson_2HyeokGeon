package com.example.shopping.domain.user.service;

import com.example.shopping.common.jwt.AuthenticationFacade;
import com.example.shopping.common.jwt.JwtTokenUtils;
import com.example.shopping.common.jwt.dto.JwtDto;
import com.example.shopping.common.jwt.dto.LoginDto;
import com.example.shopping.domain.user.dto.UserDto;
import com.example.shopping.domain.user.dto.UserUpdateDto;
import com.example.shopping.domain.user.entity.User;
import com.example.shopping.domain.user.entity.enumeration.UserAuth;
import com.example.shopping.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;

    //회원가입 - 아이디와 비밀번호만 제공받아
    public void signUp(UserDto userDto) {
        User user = User.builder()
                .userId(userDto.getUserId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .auth(UserAuth.DEACTIVE)
                .build();

        userRepository.save(user);
    }

    public JwtDto login(LoginDto dto) {
        String userId = dto.getUserId();
        User user = userRepository.findByUserId(userId);
        if (!userRepository.existsByUserId(userId))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        if (!passwordEncoder
                .matches(dto.getPassword(), user.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        // JWT 발급
        String jwt = jwtTokenUtils.generateToken(dto);
        JwtDto response = new JwtDto();
        response.setToken(jwt);
        return response;
    }

    // 회원정보 업데이트
    @Transactional
    public void update(UserUpdateDto dto) {
        User user = authenticationFacade.getLoginUser();
        // 필수 정보 입력 후 일반사용자로 자동 전환
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setNickname(dto.getNickname());
        user.setPhone(dto.getPhone());
        user.setProfileImage(dto.getProfileImage());
        user.setAuth(UserAuth.NORMAL);
    }

    // 프로필 사진 업데이트
    @Transactional
    public void updateProfileImage(byte[] filebytes) {
        User user = authenticationFacade.getLoginUser();

        user.setProfileImage(filebytes);
    }
}
