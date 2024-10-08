package com.nbe2.domain.bookmark.exception;

import com.nbe2.common.exception.DomainException;

public class BookmarkDuplicatedException extends DomainException {
    public static final BookmarkDuplicatedException EXCEPTOPN = new BookmarkDuplicatedException();

    public BookmarkDuplicatedException() {
        super(BookmarkErrorCode.BOOKMARK_DUPLICATED);
    }
}
