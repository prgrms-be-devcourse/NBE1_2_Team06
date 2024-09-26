package com.nbe2.domain.user.exception;

import static com.nbe2.common.constants.EAConstants.BAD_REQUEST;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    ALREADY_EXISTS_EMAIL(BAD_REQUEST, "USER_400_1", "이미 존재하는 이메일입니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
