package com.nbe2.domain.auth;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.user.UserFixture;

@ExtendWith(MockitoExtension.class)
class TokenManagerTest {

    @InjectMocks private TokenManager tokenManager;

    @Mock private TokenRepository tokenRepository;

    @Test
    @DisplayName("리프레시 토큰을 저장한다.")
    void given_refresh_token_then_should_save_token() {
        // given
        String token = "refreshToken";
        RefreshToken refreshToken = RefreshToken.of(UserFixture.ID, token);

        // when
        tokenManager.save(refreshToken);

        // then
        verify(tokenRepository).setRefreshToken(refreshToken);
    }
}
