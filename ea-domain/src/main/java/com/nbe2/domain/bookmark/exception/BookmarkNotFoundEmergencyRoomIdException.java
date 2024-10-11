package com.nbe2.domain.bookmark.exception;

import com.nbe2.common.exception.DomainException;

public class BookmarkNotFoundEmergencyRoomIdException extends DomainException {
    public static final BookmarkNotFoundEmergencyRoomIdException EXCEPTOPN =
            new BookmarkNotFoundEmergencyRoomIdException();

    public BookmarkNotFoundEmergencyRoomIdException() {
        super(BookmarkErrorCode.BOOKMARK_NOT_FOUND_EMERGENCY_ROOM_ID);
    }
}
