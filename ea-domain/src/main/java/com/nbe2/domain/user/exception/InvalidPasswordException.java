package com.nbe2.domain.user.exception;

import com.nbe2.common.exception.DomainException;

public class InvalidPasswordException extends DomainException {

    public static final DomainException EXCEPTION = new InvalidPasswordException();

    private InvalidPasswordException() {
        super(UserErrorCode.INVALID_PASSWORD);
    }
}
