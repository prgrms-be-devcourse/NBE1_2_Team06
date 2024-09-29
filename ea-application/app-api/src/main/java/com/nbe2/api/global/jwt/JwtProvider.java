package com.nbe2.api.global.jwt;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

    @Value("${jwt.screat-key}")
    private String SECRET_KEY;

    private Key getKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    public String getRole(String jwt) {
        return (String) getTokenClaims(getKey(), jwt).get("ROLE");
    }

    public String getUserId(String token) {
        return getTokenSubject(getKey(), token);
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
