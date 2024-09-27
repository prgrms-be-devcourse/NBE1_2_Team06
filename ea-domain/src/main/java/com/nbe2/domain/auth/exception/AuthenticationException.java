package com.nbe2.domain.auth.exception;

import com.nbe2.common.exception.DomainException;

public class AuthenticationException extends DomainException {

    public static final DomainException EXCEPTION = new AuthenticationException();

    private AuthenticationException() {
        super(AuthErrorCode.AUTHENTICATION_FAILED);
    }
}
