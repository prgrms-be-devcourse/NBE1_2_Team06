package com.nbe2.api.global.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {
    private static final String SECRET_KEY =
            "fefnfjlfkwjfelfefjkflefefnfjlfkwjfelfefjkflefefnfjlfkwjfelfefjkflefefnfjlfkwjfelfefjkflefefnfjlfkwjfelfefjkfle";

    public static String createToken(String userId) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("role", "ADMIN");

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis() + 86400000))
                .signWith(createSecreatKeySpac(), SignatureAlgorithm.HS256)
                .compact();
    }

    private static Key createSecreatKeySpac() {
        return new SecretKeySpec(
                Base64.getDecoder().decode(SECRET_KEY), SignatureAlgorithm.HS256.getJcaName());
    }

    public static String validateToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody().getSubject();
    }

    public static String getUserId(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
