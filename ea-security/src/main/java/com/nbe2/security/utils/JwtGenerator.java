package com.nbe2.security.utils;

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
<<<<<<< Updated upstream
    private static final long ACCESS_EXPIRATION_TIME = 1000 * 60 * 60; // 1시간 (3600초)
    private static final long REFRESH_EXPIRATION_TIME =
            ACCESS_EXPIRATION_TIME * 24 * 14; // 2주 (1209600초)
=======
    private static final long ACCESS_EXPIRATION_TIME = 360000; // 1시간 (3600초)
    private static final long REFRESH_EXPIRATION_TIME = 1; // 2주 (1209600초)
>>>>>>> Stashed changes

    private static String SECRET_KEY;

    @Value("${jwt.secret-key}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

    private static Key getKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    // Jwt 생성
    public Tokens generate(UserPrincipal principal) {
        return Tokens.builder()
                .accessToken(generatorAccessToken(principal))
                .refreshToken(generatorRefreshToken(principal))
                .build();
    }

    private static String generatorAccessToken(UserPrincipal principal) {
        return Jwts.builder()
                .setHeader(setHeader("ACCESS"))
                .setClaims(setClaims(principal))
                .setSubject(String.valueOf(principal.userId()))
                .setIssuedAt(getNowDate())
                .setExpiration(new Date(getNowDate().getTime() + ACCESS_EXPIRATION_TIME))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private static String generatorRefreshToken(UserPrincipal principal) {
        return Jwts.builder()
                .setHeader(setHeader("REFRESH"))
                .setClaims(setClaims(principal))
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

    //    private static Map<String, Object> setAccessClaims(UserPrincipal principal) {
    //        Map<String, Object> claims = new HashMap<>();
    //        claims.put("UserPrincpal", principal);
    //        return claims;
    //    }

    private static Map<String, Object> setClaims(UserPrincipal principal) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLE", principal.role().getRole());
        return claims;
    }

    private static Date getNowDate() {
        return new Date();
    }
}
