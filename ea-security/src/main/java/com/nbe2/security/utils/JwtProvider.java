package com.nbe2.security.utils;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nbe2.domain.auth.TokenProvider;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.user.UserRole;
import com.nbe2.security.exception.JwtExpriedException;
import com.nbe2.security.exception.JwtUnsupportedException;

import io.jsonwebtoken.*;

@Component
public class JwtProvider implements TokenProvider {

    private static String SECRET_KEY;

    @Value("${jwt.secret-key}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

    private static Key getKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    public UserPrincipal getTokenUserPrincipal(String token) {
        String roleName = getUserRole(token);
        long userId = Long.parseLong(getUserId(token));
        return UserPrincipal.of(userId, UserRole.findByRole(roleName));
    }

    private String getUserId(String token) {
        return getTokenSubject(getKey(), token);
    }

    private String getUserRole(String token) {
        return (String) getTokenClaims(getKey(), token).get("ROLE");
    }

    private static Claims getTokenClaims(Key key, String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw JwtExpriedException.EXCEPTION;
        } catch (UnsupportedJwtException e) {
            //            logger.info("지원되지 않는 JWT 토큰입니다. ");
            throw JwtUnsupportedException.EXCEPTION;
        }
    }

    private static String getTokenSubject(Key key, String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
