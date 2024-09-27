package com.nbe2.domain.auth;

public interface PasswordEncoder {

    String encode(String plainPassword);
}
