package com.nbe2.domain.auth;

public interface TokenProvider {
    UserPrincipal getUserPrincipal(String token);

    String getUserId(String token);

    String getUserRole(String token);
}
