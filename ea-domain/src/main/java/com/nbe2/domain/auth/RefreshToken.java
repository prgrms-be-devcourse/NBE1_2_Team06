package com.nbe2.domain.auth;

public record RefreshToken(long userId, String refreshToken) {

    public static RefreshToken of(long userId, String refreshToken) {
        return new RefreshToken(userId, refreshToken);
    }
}
