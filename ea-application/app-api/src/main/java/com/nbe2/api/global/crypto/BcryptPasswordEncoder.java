package com.nbe2.api.global.crypto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.PasswordEncoder;

@Component
@RequiredArgsConstructor
public class BcryptPasswordEncoder implements PasswordEncoder {

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public String encode(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    @Override
    public boolean isPasswordUnmatched(String plainPassword, String encodedPassword) {
        return !passwordEncoder.matches(plainPassword, encodedPassword);
    }
}
