package com.nbe2.domain.auth;

import static com.nbe2.domain.global.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.auth.exception.AuthenticationException;
import com.nbe2.domain.user.UserAppender;
import com.nbe2.domain.user.UserFixture;
import com.nbe2.domain.user.UserProfile;
import com.nbe2.domain.user.UserRole;
import com.nbe2.domain.user.UserValidator;
import com.nbe2.domain.user.exception.AlreadyExistsEmailException;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks private AuthService authService;

    @Mock private TokenGenerator tokenGenerator;

    @Mock private TokenManager tokenManager;

    @Mock private Authenticator authenticator;

    @Mock private UserValidator userValidator;

    @Mock private UserAppender userAppender;

    @Nested
    @DisplayName("회원 가입을 테스트한다.")
    class SignupTest {

        @Test
        @DisplayName("유효한 이메일로 사용자가 회원 가입한다.")
        void given_valid_email_when_sign_up_then_should_append_user() {
            // given
            UserProfile userProfile = UserFixture.createUserProfile();
            Optional<Long> emergencyRoomId = Optional.empty();
            Optional<Long> fileId = Optional.empty();

            // when
            authService.signUp(userProfile, emergencyRoomId, fileId);

            // then
            verify(userValidator).validate(userProfile.email());
            verify(userAppender).append(userProfile);
        }

        @Test
        @DisplayName("중복된 이메일로 회원 가입 시 예외가 발생한다.")
        void given_invalid_email_when_sign_up_then_should_throw_exception() {
            // given
            UserProfile userProfile = UserFixture.createUserProfile();
            Optional<Long> emergencyRoomId = Optional.empty();
            Optional<Long> fileId = Optional.empty();

            // when
            doThrow(AlreadyExistsEmailException.class).when(userValidator).validate(anyString());

            // then
            verify(userAppender, never()).append(any(UserProfile.class));
            assertThrows(
                    AlreadyExistsEmailException.class,
                    () -> authService.signUp(userProfile, emergencyRoomId, fileId));
        }
    }

    @Nested
    @DisplayName("로그인을 테스트한다.")
    class LoginTest {

        @Test
        @DisplayName("유효한 로그인 정보로 로그인 시 토큰을 발급한다.")
        void given_valid_login_when_login_then_should_generate_token() {
            // given
            Login login = new Login(EMAIL, PASSWORD);
            UserPrincipal principal = UserPrincipal.of(ID, UserRole.USER);
            Tokens expected = new Tokens("access token", "refresh token");

            // when
            when(authenticator.authenticate(any(Login.class))).thenReturn(principal);
            when(tokenGenerator.generate(any(UserPrincipal.class))).thenReturn(expected);

            // then
            Tokens actual = authService.login(login);
            verify(authenticator).authenticate(login);
            verify(tokenGenerator).generate(principal);
            verify(tokenManager).save(RefreshToken.of(ID, expected.refreshToken()));
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("인증 실패 시 예외가 발생한다.")
        void given_invalid_login_when_login_then_should_throw_exception() {
            // given
            Login login = new Login(EMAIL, PASSWORD);

            // when
            doThrow(AuthenticationException.class)
                    .when(authenticator)
                    .authenticate(any(Login.class));

            // then
            assertThrows(AuthenticationException.class, () -> authService.login(login));
        }
    }
}
