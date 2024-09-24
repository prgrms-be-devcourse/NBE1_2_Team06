package com.nbe2.api.global.exception;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {
    PERMISSION_DENIED(403, "PERMISSION_DENIED", "해당 API 권한이 없습니다");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
