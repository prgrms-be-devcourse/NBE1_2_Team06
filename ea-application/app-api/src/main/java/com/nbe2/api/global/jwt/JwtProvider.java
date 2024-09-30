package com.nbe2.api.global.jwt;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nbe2.domain.auth.TokenProvider;
import com.nbe2.domain.auth.UserPrincipal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider implements TokenProvider {

    private static String SECRET_KEY;

    @Value("${jwt.screat-key}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

    private static Key getKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    public UserPrincipal getUserPrincipal(String token) {
        return (UserPrincipal) getTokenClaims(getKey(), token).get("UserPrincpal");
    }

    public String getUserId(String token) {
        return getTokenSubject(getKey(), token);
    }

    public String getUserRole(String token) {
        return (String) getTokenClaims(getKey(), token).get("ROLE");
    }

    private static Claims getTokenClaims(Key key, String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
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
