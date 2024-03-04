package com.example.shopping.service;

import com.example.shopping.dto.JwtDto;
import com.example.shopping.dto.LoginDto;
import com.example.shopping.dto.UserDto;
import com.example.shopping.dto.UserUpdateDto;
import com.example.shopping.entity.*;
import com.example.shopping.entity.enumeration.MallStatus;
import com.example.shopping.entity.enumeration.UserAuth;
import com.example.shopping.jwt.AuthenticationFacade;
import com.example.shopping.jwt.JwtTokenUtils;
import com.example.shopping.repository.BusinessRepository;
import com.example.shopping.repository.MallRepository;
import com.example.shopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;
    private final MallRepository mallRepository;



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


    // 사업자 사용자 신청
    @Transactional
    public void updateBusinessNum(UserUpdateDto dto) {
        User findUser = authenticationFacade.getLoginUser();
        // 일반 사용자 일때
        if (!findUser.getAuth().equals(UserAuth.NORMAL)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        BusinessRegistration build = BusinessRegistration.builder().
                user(findUser).
                businessNum(dto.getBusinessNum()).
                build();

        businessRepository.save(build);
    }

    // 사업자 사용자 신청 목록
    public List<BusinessRegistration> readBusinessRegistration() {
        User findUser = authenticationFacade.getLoginUser();
        if (!findUser.getAuth().equals(UserAuth.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return businessRepository.findAll();
    }

    // 사업자 사용자 신청 수락
    @Transactional
    public void acceptBusinessRegistration(Long id) {

        User user = authenticationFacade.getLoginUser();
        if (!user.getAuth().equals(UserAuth.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Optional<BusinessRegistration> byId = businessRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User user1 = byId.get().getUser();
        user1.setAuth(UserAuth.BUSINESS);
        // 준비 중 상태의 쇼핑몰 추가
        Mall build = Mall.builder().mallStatus(MallStatus.PREPARING).user(user1).build();

        mallRepository.save(build);

    }

    @Transactional
    public void declineBusinessRegistration(Long id) {
        User user = authenticationFacade.getLoginUser();
        if (!user.getAuth().equals(UserAuth.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Optional<BusinessRegistration> byId = businessRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        businessRepository.deleteById(id);
    }

}
