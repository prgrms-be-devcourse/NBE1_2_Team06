package com.nbe2.domain.bookmark.exception;

import com.nbe2.common.exception.DomainException;

public class BookmarkNotYoursException extends DomainException {
    public static final BookmarkNotYoursException EXCEPTOPN = new BookmarkNotYoursException();

    public BookmarkNotYoursException() {
        super(BookmarkErrorCode.BOOKMARK_NOT_YOURS);
    }
}
