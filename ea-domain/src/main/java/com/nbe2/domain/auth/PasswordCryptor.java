package com.nbe2.domain.auth;

public interface PasswordCryptor {

    String encode(String plainPassword);

    boolean isPasswordUnmatched(String plainPassword, String encodedPassword);
}
