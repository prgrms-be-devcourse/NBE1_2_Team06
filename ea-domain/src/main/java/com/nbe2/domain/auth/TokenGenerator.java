package com.nbe2.domain.auth;

public interface TokenGenerator {

    String generateToken(UserPrincipal principal, long expirationTime);
}
