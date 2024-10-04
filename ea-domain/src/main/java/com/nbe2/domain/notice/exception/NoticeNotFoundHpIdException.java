package com.nbe2.domain.notice.exception;

import com.nbe2.common.exception.DomainException;

public class NoticeNotFoundHpIdException extends DomainException {
    public static final DomainException EXCEPTION = new NoticeNotFoundHpIdException();

    private NoticeNotFoundHpIdException() {
        super(NoticeErrorCode.NOTICE_NOT_FOUND_HOSPITAL);
    }
}
