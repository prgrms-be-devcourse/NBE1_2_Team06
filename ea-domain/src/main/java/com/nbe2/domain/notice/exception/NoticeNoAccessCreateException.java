package com.nbe2.domain.notice.exception;

import com.nbe2.common.exception.DomainException;

public class NoticeNoAccessCreateException extends DomainException {
    public static final DomainException EXCEPTION = new NoticeNoAccessCreateException();

    private NoticeNoAccessCreateException() {
        super(NoticeErrorCode.NOTICE_NO_ACCESS_CREATE);
    }
}
