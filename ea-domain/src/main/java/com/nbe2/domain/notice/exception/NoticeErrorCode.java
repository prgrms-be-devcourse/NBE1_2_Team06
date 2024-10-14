package com.nbe2.domain.notice.exception;

import static com.nbe2.common.constants.EAConstants.BAD_REQUEST;
import static com.nbe2.common.constants.EAConstants.FORBIDDEN;
import static com.nbe2.common.constants.EAConstants.NOT_FOUND;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum NoticeErrorCode implements BaseErrorCode {
    NOTICE_NOT_FOUND_NOTICE_ID(NOT_FOUND, "NOTICE_404_1", "존재하지 않는 공지사항 ID 입니다."),
    NOTICE_NO_ACCESS_CREATE(BAD_REQUEST, "NOTICE_400_1", "의료관계자만 작성할 수 있습니다."),
    NOTICE_NO_ACCESS_YOURS(FORBIDDEN, "NOTICE_403_1", "본인이 작성한 공지사항만 수정,삭제 할 수 있습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
