package com.nbe2.infra.auth;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.PasswordEncoder;

@Component
@RequiredArgsConstructor
public class SecurityPasswordEncoder implements PasswordEncoder {

    //    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String encode(String plainPassword) {
        return plainPassword; // @TODO Spring Security 적용하면 BCryptPasswordEncoder가 인코딩하게 수정
    }
}
