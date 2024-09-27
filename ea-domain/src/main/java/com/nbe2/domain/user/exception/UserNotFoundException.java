package com.nbe2.domain.user.exception;

import com.nbe2.common.exception.DomainException;

public class UserNotFoundException extends DomainException {

    public static final DomainException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
