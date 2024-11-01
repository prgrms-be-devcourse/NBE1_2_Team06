package com.nbe2.domain.auth.exception;

import static com.nbe2.common.constants.EAConstants.UNAUTHORIZED;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {
    AUTHENTICATION_FAILED(UNAUTHORIZED, "AUTH_401_1", "로그인에 실패했습니다."),
    REFRESH_TOKEN_NOT_FOUND(UNAUTHORIZED, "AUTH_401_2", "Refresh Token이 유효하지 않습니다. 다시 로그인 해주세요.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
