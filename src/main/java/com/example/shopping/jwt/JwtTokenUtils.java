package com.example.shopping.jwt;

import com.example.shopping.jwt.dto.LoginDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenUtils {
    // JWT를 만드는 용도의 암호키
    private final Key signingkey;
    // JWT를 해석하는 용도의 객체
    private final JwtParser jwtParser;

    public JwtTokenUtils(@Value("${jwt.secret}") String jwtSecret) {
        this.signingkey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(this.signingkey)
                .build();
    }

    public String generateToken(LoginDto dto) {
        // JWT에 담고싶은 정보를 Claims로 만든다.

        // 현재 호출되었을 때 epoch time
        Instant now = Instant.now();
        Claims jwtClaims = Jwts.claims()
                // sub: 누구인지
                .setSubject(dto.getUserId())
                // iat: 언제 발급 되었는지
                .setIssuedAt(Date.from(now))
                // exp: 언제 만료 예정인지
                .setExpiration(Date.from(now.plusSeconds(60 * 60 * 10)));
        // 최종적으로 JWT를 발급한다.
        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(this.signingkey)
                .compact();
    }

    // 정상적인 JWT인지를 판단하는 메서드
    public boolean validate(String token) {
        try {
            // 정상적이지 않은 JWT라면 예외(Exception)가 발생한다.
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.warn("invalid jwt");
        }
        return false;
    }

    // 실제 데이터(Payload)를 반환하는 메서드
    public Claims parseClaims(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody();
    }
}
