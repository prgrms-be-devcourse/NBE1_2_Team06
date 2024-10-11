package com.nbe2.domain.notice.exception;

import com.nbe2.common.exception.DomainException;

public class NoticeNoAccessYoursException extends DomainException {
    public static final DomainException EXCEPTION = new NoticeNoAccessYoursException();

    private NoticeNoAccessYoursException() {
        super(NoticeErrorCode.NOTICE_NO_ACCESS_YOURS);
    }
}
