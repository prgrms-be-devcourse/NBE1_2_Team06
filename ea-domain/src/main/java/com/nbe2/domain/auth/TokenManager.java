package com.nbe2.domain.auth;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenManager {

    private final TokenRepository tokenRepository;

    public void save(RefreshToken refreshToken) {
        tokenRepository.setRefreshToken(refreshToken);
    }
}
