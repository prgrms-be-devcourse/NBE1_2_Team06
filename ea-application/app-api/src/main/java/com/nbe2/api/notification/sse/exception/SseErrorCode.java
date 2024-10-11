package com.nbe2.api.notification.sse.exception;

import static com.nbe2.common.constants.EAConstants.INTERNAL_SERVER;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum SseErrorCode implements BaseErrorCode {
    SSE_CONNECTION_FAILED(INTERNAL_SERVER, "SSE_500_1", "SSE 연결에 실패했습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
