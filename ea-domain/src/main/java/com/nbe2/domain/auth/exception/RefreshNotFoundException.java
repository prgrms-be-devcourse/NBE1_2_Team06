package com.nbe2.domain.auth.exception;

import com.nbe2.common.exception.DomainException;

public class RefreshNotFoundException extends DomainException {
    public static final RefreshNotFoundException EXCEPTION = new RefreshNotFoundException();

    private RefreshNotFoundException() {
        super(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
