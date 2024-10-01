package com.nbe2.domain.auth;

public interface TokenProvider {
    UserPrincipal getTokenUserPrincipal(String token);
}
