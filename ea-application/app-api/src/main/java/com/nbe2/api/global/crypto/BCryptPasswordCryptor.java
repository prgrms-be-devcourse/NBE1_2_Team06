package com.nbe2.api.global.crypto;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.PasswordCryptor;

@Component
@RequiredArgsConstructor
public class BCryptPasswordCryptor implements PasswordCryptor {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    @Override
    public boolean isPasswordUnmatched(String plainPassword, String encodedPassword) {
        return !passwordEncoder.matches(plainPassword, encodedPassword);
    }
}
