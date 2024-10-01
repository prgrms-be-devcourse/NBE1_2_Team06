package com.nbe2.domain.emergencyroom.exception;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum TokenErrorCode implements BaseErrorCode {
    REFRESH_TOKEN_NOT_FOUND_ERROR(
            HttpStatus.BAD_REQUEST.value(), "EMR_400", "Refresh Token이 유효하지 않습니다. 다시 로그인 해주세요."),
    ;
    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
