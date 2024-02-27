package com.example.shopping.controller;

import com.example.shopping.dto.JwtDto;
import com.example.shopping.dto.LoginDto;
import com.example.shopping.jwt.JwtTokenUtils;
import com.example.shopping.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class TokenController {
    // JWT를 발급하기 위한 Bean
    private final JwtTokenUtils jwtTokenUtils;
    private final UserService userService;

    @PostMapping("/signin")
    public JwtDto issueJwt(
            @RequestBody LoginDto dto
    ) {
       return userService.login(dto);
    }

    @GetMapping("/validate")
    public Claims validateToken(
            @RequestParam("token")
            String token
    ) {
        if (!jwtTokenUtils.validate(token))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return jwtTokenUtils.parseClaims(token);
    }
}