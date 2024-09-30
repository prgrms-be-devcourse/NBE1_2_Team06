package com.nbe2.api.global.jwt;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nbe2.api.global.exception.JwtNotValidateException;
import com.nbe2.api.global.exception.JwtUnsupportedException;
import com.nbe2.domain.auth.TokenValidator;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtValidator implements TokenValidator {

    private static String SECRET_KEY;

    @Value("${jwt.screat-key}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

    private static Key getKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    // Jwt 검증
    public boolean checkJwt(String jwt) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(jwt);
            return true;
        } catch (ExpiredJwtException e) {
            throw JwtNotValidateException.EXCEPTION;
        } catch (UnsupportedJwtException e) {
            //            logger.info("지원되지 않는 JWT 토큰입니다. ");
            throw JwtUnsupportedException.EXCEPTION;
        }
    }
}
