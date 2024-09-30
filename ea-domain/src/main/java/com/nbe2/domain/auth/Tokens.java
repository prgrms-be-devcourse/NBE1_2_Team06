package com.nbe2.domain.auth;

import lombok.Builder;

import com.nbe2.domain.user.UserRole;

@Builder
public record Tokens(String accessToken, String refreshToken, Userinfo userInfo) {

    @Builder
    public static class Userinfo {
        String userId;
        UserRole userRole;
    }

    public Tokens to() {
        return Tokens.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }
}
