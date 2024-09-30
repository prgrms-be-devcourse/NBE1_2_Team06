package com.nbe2.api.global.util;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.jwt.JwtProvider;
import com.nbe2.api.global.jwt.JwtValidator;

import io.jsonwebtoken.*;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final JwtProvider provider;
    private final JwtValidator jwtValidator;

    // Jwt 검증
    public boolean validateJwt(String jwt) {
        return jwtValidator.checkJwt(jwt);
    }

    // 유저의 권한 반환
    public String getRole(String jwt) {
        return provider.getRole(jwt);
    }

    // 유저 ID 반환
    public String getUserId(String jwt) {
        return provider.getUserId(jwt);
    }
}
