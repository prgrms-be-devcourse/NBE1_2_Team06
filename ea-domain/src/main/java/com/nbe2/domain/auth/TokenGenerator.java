package com.nbe2.domain.auth;

public interface TokenGenerator {

    Tokens generateToken(UserPrincipal principal);
}