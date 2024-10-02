package com.nbe2.api.auth.dto;

import lombok.Builder;

import com.nbe2.domain.auth.Tokens;
import com.nbe2.domain.user.UserRole;

@Builder
public record TokensRequest(String accessToken, String refreshToken) {
    @Builder
    public static class Userinfo {
        String userId;
        UserRole userRole;
    }

    public Tokens to() {
        return Tokens.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }
}
