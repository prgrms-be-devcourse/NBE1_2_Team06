package com.nbe2.api.global.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nbe2.domain.auth.TokenGenerator;
import com.nbe2.domain.auth.Tokens;
import com.nbe2.domain.auth.UserPrincipal;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator implements TokenGenerator {
    private static final long ACCESS_EXPIRATION_TIME = 80; // -> 약 10분의 토큰 유효 기간
    private static final long REFRESH_EXPIRATION_TIME = 86; // -> 약 하루의 토큰 유효 기간

    @Value("${jwt.screat-key}")
    private String SECRET_KEY;

    private Key getKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    @Override
    // Jwt 생성
    public Tokens generateToken(UserPrincipal principal) {
        return Tokens.builder()
                .accessToken(generatorAccessToken(principal))
                .refreshToken(generatorRefreshToken(principal))
                .build();
    }

    @Override
    public Tokens createAccessToken(UserPrincipal userPrincipal, String refreshToken) {
        return Tokens.builder()
                .accessToken(generatorAccessToken(userPrincipal))
                .refreshToken(refreshToken)
                .build();
    }

    private String generatorAccessToken(UserPrincipal principal) {
        return Jwts.builder()
                .setHeader(setHeader("ACCESS"))
                .setClaims(setClaims(principal))
                .setSubject(String.valueOf(principal.userId()))
                .setIssuedAt(getNowDate())
                .setExpiration(new Date(getNowDate().getTime() + ACCESS_EXPIRATION_TIME))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String generatorRefreshToken(UserPrincipal principal) {

        return Jwts.builder()
                .setHeader(setHeader("REFRESH"))
                .setSubject(String.valueOf(principal.userId()))
                .setIssuedAt(getNowDate())
                .setExpiration(new Date(getNowDate().getTime() + REFRESH_EXPIRATION_TIME))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private static Map<String, Object> setHeader(String type) {
        Map<String, Object> header = new HashMap<>();
        header.put("type", "JWT");
        header.put("tokenType", type);
        header.put("alg", "HS256");
        return header;
    }

    private static Map<String, Object> setClaims(UserPrincipal principal) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLE", principal.role());
        return claims;
    }

    private Date getNowDate() {
        return new Date();
    }
}
