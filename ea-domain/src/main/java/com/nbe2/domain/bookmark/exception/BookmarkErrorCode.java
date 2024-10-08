package com.nbe2.domain.bookmark.exception;

import static com.nbe2.common.constants.EAConstants.BAD_REQUEST;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum BookmarkErrorCode implements BaseErrorCode {
    BOOKMARK_NOT_FOUND_EMERGENCY_ROOM_ID(BAD_REQUEST, "BOOKMARK_404_1", "즐겨찾기한 병원이 아닙니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
