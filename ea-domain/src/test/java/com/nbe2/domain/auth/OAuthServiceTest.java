package com.nbe2.domain.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserAppender;
import com.nbe2.domain.user.UserFixture;
import com.nbe2.domain.user.UserReader;
import com.nbe2.domain.user.UserValidator;

@ExtendWith(MockitoExtension.class)
class OAuthServiceTest {

    @InjectMocks private OAuthService oAuthService;

    @Mock private OAuthClient oAuthClient;

    @Mock private TokenManager tokenManager;

    @Mock private TokenGenerator tokenGenerator;

    @Mock private UserValidator userValidator;

    @Mock private UserAppender userAppender;

    @Mock private UserReader userReader;

    @Test
    @DisplayName("소셜 계정을 처음 연동 시 회원 가입 후 토큰을 발급한다.")
    void when_user_does_not_exist_then_should_append_user_and_generate_token() {
        // given
        String code = "code";
        OAuthProfile oAuthProfile = AuthFixture.createOAuthProfile();
        User user = UserFixture.createUserWithId();
        Tokens expected = new Tokens("access_token", "refresh_token");

        // when
        when(oAuthClient.getOAuthProfile(anyString())).thenReturn(oAuthProfile);
        when(userValidator.isEmailExists(anyString())).thenReturn(false);
        when(userReader.read(anyString())).thenReturn(user);
        when(tokenGenerator.generate(any(UserPrincipal.class))).thenReturn(expected);

        Tokens actual = oAuthService.login(code);

        // then
        verify(userAppender).append(oAuthProfile);
        verify(tokenManager).save(RefreshToken.of(user.getId(), expected.refreshToken()));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("소셜 로그인 시 토큰을 발급한다.")
    void when_user_login_by_oauth_then_should_generate_token() {
        // given
        String code = "code";
        OAuthProfile oAuthProfile = AuthFixture.createOAuthProfile();
        User user = UserFixture.createUserWithId();
        Tokens expected = new Tokens("access_token", "refresh_token");

        // when
        when(oAuthClient.getOAuthProfile(anyString())).thenReturn(oAuthProfile);
        when(userValidator.isEmailExists(anyString())).thenReturn(true);
        when(userReader.read(anyString())).thenReturn(user);
        when(tokenGenerator.generate(any(UserPrincipal.class))).thenReturn(expected);

        Tokens actual = oAuthService.login(code);

        // then
        verify(userAppender, never()).append(oAuthProfile);
        verify(tokenManager).save(RefreshToken.of(user.getId(), expected.refreshToken()));
        assertEquals(expected, actual);
    }
}
