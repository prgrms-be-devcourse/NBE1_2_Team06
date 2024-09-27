package com.nbe2.domain.user.exception;

import com.nbe2.common.exception.DomainException;

public class AlreadyExistsEmailException extends DomainException {

    public static final DomainException EXCEPTION = new AlreadyExistsEmailException();

    private AlreadyExistsEmailException() {
        super(UserErrorCode.ALREADY_EXISTS_EMAIL);
    }
}
