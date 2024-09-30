package com.nbe2.domain.posts.exception;

import static com.nbe2.common.constants.EAConstants.NOT_FOUND;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum PostErrorCode implements BaseErrorCode {
    POST_NOT_FOUND(NOT_FOUND, "POST_404_1", "존재하지 않는 게시물입니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
