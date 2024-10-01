package com.nbe2.domain.auth;

import lombok.Builder;

@Builder
public record Tokens(String accessToken, String refreshToken) {

    public RefreshToken getRefreshToken(long userId) {
        return RefreshToken.of(userId, refreshToken);
    }
}
