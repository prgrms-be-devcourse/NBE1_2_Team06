package com.nbe2.domain.emergencyroom.exception;

import com.nbe2.common.exception.DomainException;

public class RefreshNotFountException extends DomainException {
    public static final RefreshNotFountException EXCEPTION = new RefreshNotFountException();

    private RefreshNotFountException() {
        super(TokenErrorCode.REFRESH_TOKEN_NOT_FOUND_ERROR);
    }
}
