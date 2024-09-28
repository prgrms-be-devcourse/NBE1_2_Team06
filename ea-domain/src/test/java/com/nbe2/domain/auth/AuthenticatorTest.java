package com.nbe2.domain.auth;

import static com.nbe2.domain.global.TestConstants.EMAIL;
import static com.nbe2.domain.global.TestConstants.PASSWORD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.auth.exception.AuthenticationException;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;
import com.nbe2.domain.user.UserReader;
import com.nbe2.domain.user.exception.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
class AuthenticatorTest {

    @InjectMocks private Authenticator authenticator;

    @Mock private PasswordEncoder passwordEncoder;

    @Mock private UserReader userReader;

    @Test
    @DisplayName("유효한 로그인 정보로 로그인 시 인증에 성공한다.")
    void given_valid_email_password_when_authenticate_then_login_success() {
        // given
        Login login = new Login(EMAIL, PASSWORD);
        User user = UserFixture.createUserWithId();
        UserPrincipal expected = UserPrincipal.of(user.getId(), user.getRole());

        // when
        when(userReader.read(anyString())).thenReturn(user);
        when(passwordEncoder.isPasswordUnmatched(anyString(), anyString())).thenReturn(false);

        // then
        UserPrincipal actual = authenticator.authenticate(login);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("존재하지 않는 이메일 로그인 시 예외가 발생한다.")
    void given_invalid_email_when_authenticate_then_should_throw_exception() {
        // given
        Login login = new Login(EMAIL, PASSWORD);

        // when
        doThrow(UserNotFoundException.class).when(userReader).read(anyString());

        // then
        assertThrows(UserNotFoundException.class, () -> authenticator.authenticate(login));
    }

    @Test
    @DisplayName("비밀번호가 일치하지 않으면 예외가 발생한다.")
    void given_invalid_password_when_authenticate_then_should_throw_exception() {
        // given
        Login login = new Login(EMAIL, PASSWORD);
        User user = UserFixture.createUser();

        // when
        when(userReader.read(anyString())).thenReturn(user);
        when(passwordEncoder.isPasswordUnmatched(anyString(), anyString())).thenReturn(true);

        // then
        assertThrows(AuthenticationException.class, () -> authenticator.authenticate(login));
    }
}
