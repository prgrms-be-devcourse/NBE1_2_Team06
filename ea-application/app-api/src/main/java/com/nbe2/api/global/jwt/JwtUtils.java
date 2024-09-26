package com.nbe2.api.global.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;

import com.nbe2.common.exception.GlobalErrorCode;
import com.nbe2.common.exception.WebException;

import io.jsonwebtoken.*;

public class JwtUtils {

    @Value("${jwt.key}")
    private static String SECRET_KEY;

    private static final long EXPIRATION_TIME = 86400000; // -> 약 하루의 토큰 유효 기간

    // Access Token 생성
    public static String createAccessToken(String username, String role) {
        return createJwt(username, role);
    }

    // jwt 생성
    // 권한 설정은 임시
    private static String createJwt(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);
        Date time = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(time)
                .setExpiration(new Date(time.getTime() + EXPIRATION_TIME))
                //                .setExpiration(new Date(time.getTime() + 1))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Jwt 서명 발급
    private static Key getKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    // Jwt에서 username 추출
    public static String getUserName(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

    // Jwt 검증
    public static boolean validateJwt(String jwt) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(jwt);
            return true;
        } catch (ExpiredJwtException e) {
            throw new WebException(GlobalErrorCode.OTHER_SERVER_EXPIRED_TOKEN);
        } catch (IllegalArgumentException e) {
            throw new WebException(GlobalErrorCode.OTHER_SERVER_NOT_FOUND);
        }
    }
}
