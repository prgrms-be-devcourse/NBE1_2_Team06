package com.nbe2.domain.auth;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.UserRole;

@Component
@RequiredArgsConstructor
public class TokenManager {

    private final TokenRepository tokenRepository;
    private final TokenGenerator tokenGenerator;

    public void save(RefreshToken refreshToken) {
        tokenRepository.setRefreshToken(refreshToken);
    }

    public void removeRefreshToken(long userId) {
        tokenRepository.removeRefreshToken(userId);
    }

    public Tokens updateToken(Tokens tokens) {
        RefreshToken refreshToken = getRefreshToken(Long.parseLong(tokens.userInfo().userId));
        if (refreshToken != null) {
            return tokenGenerator.createAccessToken(
                    UserPrincipal.of(refreshToken.userId(), UserRole.USER),
                    refreshToken.refreshToken());
        } else {
            Tokens newTokens =
                    tokenGenerator.generateToken(
                            UserPrincipal.of(
                                    Long.parseLong(tokens.userInfo().userId),
                                    tokens.userInfo().userRole));
            save(
                    RefreshToken.of(
                            Long.parseLong(tokens.userInfo().userId), newTokens.refreshToken()));
            return newTokens;
        }
    }

    private RefreshToken getRefreshToken(long userId) {
        return tokenRepository.getRefreshToken(userId);
    }
}
