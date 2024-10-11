package com.nbe2.domain.review.exception;

import static com.nbe2.common.constants.EAConstants.BAD_REQUEST;
import static com.nbe2.common.constants.EAConstants.NOT_FOUND;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    REVIEW_NOT_FOUND(NOT_FOUND, "REVIEW_404_1", "존재하지 않는 리뷰 입니다."),
    REVIEW_NO_ACCESS_AUTHORITY(BAD_REQUEST, "REVIEW_400_1", "본인이 작성한 리뷰가 아닙니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
