package com.nbe2.domain.auth;

public interface TokenValidator {

    boolean checkJwt(String jwt);
}
