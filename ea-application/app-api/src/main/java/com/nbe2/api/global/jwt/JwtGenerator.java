package com.nbe2.api.global.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nbe2.domain.auth.TokenGenerator;
import com.nbe2.domain.auth.UserPrincipal;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator implements TokenGenerator {
    private static final long EXPIRATION_TIME = 86400000; // -> 약 하루의 토큰 유효 기간

    @Value("${jwt.screat-key}")
    private String SECRET_KEY;

    private Key getKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    @Override
    // Jwt 생성
    public String generateToken(UserPrincipal principal, long expirationTime) {
        Date date = new Date();
        return Jwts.builder()
                .setHeader(setHeader())
                .setClaims(setClaims(principal))
                .setSubject(String.valueOf(principal.userId()))
                //                .setIssuedAt(time)
                .setExpiration(new Date(date.getTime() + EXPIRATION_TIME))
                //                .setExpiration(new Date(time.getTime() + 1))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private static Map<String, Object> setHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("type", "JWT");
        header.put("alg", "HS256");
        return header;
    }

    private static Map<String, Object> setClaims(UserPrincipal principal) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLE", principal.role());
        return claims;
    }
}
