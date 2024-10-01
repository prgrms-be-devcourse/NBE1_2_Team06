package com.nbe2.domain.auth;

public interface TokenGenerator {

    Tokens generate(UserPrincipal principal);
}
