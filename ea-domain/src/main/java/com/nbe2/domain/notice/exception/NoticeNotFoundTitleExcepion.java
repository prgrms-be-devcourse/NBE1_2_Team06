package com.nbe2.domain.notice.exception;

import com.nbe2.common.exception.DomainException;

public class NoticeNotFoundTitleExcepion extends DomainException {
    public static final DomainException EXCEPTION = new NoticeNotFoundTitleExcepion();

    private NoticeNotFoundTitleExcepion() {
        super(NoticeErrorCode.NOTICE_NOT_FOUND_TITLE);
    }
}
