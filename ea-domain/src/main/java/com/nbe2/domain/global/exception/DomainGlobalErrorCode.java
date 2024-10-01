package com.nbe2.domain.global.exception;

import static com.nbe2.common.constants.EAConstants.INTERNAL_SERVER;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum DomainGlobalErrorCode implements BaseErrorCode {
    ILLEGAL_LOCKING_ATTEMPT(INTERNAL_SERVER, "DOMAIN_900_1", "잘못된 락킹 시도입니다.");
    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
