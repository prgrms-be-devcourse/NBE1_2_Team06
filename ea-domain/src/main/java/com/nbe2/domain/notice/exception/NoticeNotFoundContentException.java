package com.nbe2.domain.notice.exception;

import com.nbe2.common.exception.DomainException;

public class NoticeNotFoundContentException extends DomainException {
    public static final DomainException EXCEPTION = new NoticeNotFoundContentException();

    private NoticeNotFoundContentException() {
        super(NoticeErrorCode.NOTICE_NOT_FOUND_CONTENT);
    }
}
