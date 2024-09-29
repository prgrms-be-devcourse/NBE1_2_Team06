package com.nbe2.domain.notice.exception;

import static com.nbe2.common.constants.EAConstants.NOT_FOUND;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum NoticeErrorCode implements BaseErrorCode {
    NOTICE_NOT_FOUND_HOSPITAL(NOT_FOUND, "NOTICE_404_1", "존재하지 않는 병원 ID 입니다."),
    NOTICE_NOT_FOUND_TITLE(NOT_FOUND, "NOTICE_404_2", "제목이 없습니다."),
    NOTICE_NOT_FOUND_CONTENT(NOT_FOUND, "NOTICE_404_3", "내용이 없습니다."),
    NOTICE_NOT_FOUND_NOTICE_ID(NOT_FOUND, "NOTICE_404_4", "존재하지 않는 공지사항 ID 입니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
