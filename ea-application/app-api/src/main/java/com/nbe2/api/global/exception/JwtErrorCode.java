package com.nbe2.api.global.exception;

import static com.nbe2.common.constants.EAConstants.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum JwtErrorCode implements BaseErrorCode {
    UNSUPPORTED_TOKEN(BAD_REQUEST, "TOKEN_400", "지원 하지 않은 토큰"),
    EXPIRED_TOKEN(UNAUTHORIZED, "TOKEN_401", "만료된 토큰"),
    TOKEN_NOT_VALIDATE(UNAUTHORIZED, "TOKEN_401", "유효하지 않은 토큰"),
    TOKEN_BAD_SIGNATURE(UNAUTHORIZED, "TOKEN_401", "서명 불일치"),
    REFRESH_TOKEN_EXPIRED(UNAUTHORIZED, "TOKEN_401", "만료된 리프레쉬 토큰"),
    ACCESS_TOKEN_NOT_FOUNT(NOT_FOUND, "TOKEN_404", "ACCESS TOKEN이 없음");
    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
