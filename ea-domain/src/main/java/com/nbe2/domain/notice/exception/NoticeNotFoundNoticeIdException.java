package com.nbe2.domain.notice.exception;

import com.nbe2.common.exception.DomainException;

public class NoticeNotFoundNoticeIdException extends DomainException {
	public static final DomainException EXCEPTION = new NoticeNotFoundNoticeIdException();

	private NoticeNotFoundNoticeIdException() {
		super(NoticeErrorCode.NOTICE_NOT_FOUND_NOTICE_ID);
	}
}
