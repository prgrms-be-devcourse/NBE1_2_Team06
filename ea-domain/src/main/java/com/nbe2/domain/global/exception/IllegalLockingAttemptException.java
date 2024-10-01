package com.nbe2.domain.global.exception;

import com.nbe2.common.exception.DomainException;

public class IllegalLockingAttemptException extends DomainException {
    public static final DomainException EXCEPTION = new IllegalLockingAttemptException();

    private IllegalLockingAttemptException() {
        super(DomainGlobalErrorCode.ILLEGAL_LOCKING_ATTEMPT);
    }
}
