package com.nbe2.domain.auth;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.exception.AuthenticationException;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Component
@RequiredArgsConstructor
public class Authenticator {

    private final PasswordEncoder passwordEncoder;
    private final UserReader userReader;

    public UserPrincipal authenticate(Login login) {
        User user = userReader.read(login.email());
        validatePassword(login.password(), user.getPassword());

        return UserPrincipal.of(user.getId(), user.getRole());
    }

    private void validatePassword(String plain, String encoded) {
        if (passwordEncoder.isPasswordUnmatched(plain, encoded)) {
            throw AuthenticationException.EXCEPTION;
        }
    }
}
