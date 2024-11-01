package com.nbe2.domain.auth;

import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.exception.RefreshNotFoundException;

@Component
@RequiredArgsConstructor
public class TokenManager {

    private final TokenRepository tokenRepository;

    public void save(RefreshToken refreshToken) {
        tokenRepository.setRefreshToken(refreshToken);
    }

    public void removeRefreshToken(long userId) {
        tokenRepository.removeRefreshToken(userId);
    }

    public void checkRefreshToken(long userId) {
        Optional<RefreshToken> refreshToken = tokenRepository.getRefreshToken(userId);
        // redis에 값이 없을 경우 에러를 발생시키고 클라쪽에 로그인창으로 리다이렉트 요청
        refreshToken.orElseThrow(() -> RefreshNotFoundException.EXCEPTION);
    }
}
