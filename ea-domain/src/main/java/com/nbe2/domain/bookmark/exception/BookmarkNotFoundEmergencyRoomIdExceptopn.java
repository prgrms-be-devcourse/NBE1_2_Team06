package com.nbe2.domain.bookmark.exception;

import com.nbe2.common.exception.DomainException;

public class BookmarkNotFoundEmergencyRoomIdExceptopn extends DomainException {
    public static final BookmarkNotFoundEmergencyRoomIdExceptopn EXCEPTOPN =
            new BookmarkNotFoundEmergencyRoomIdExceptopn();

    public BookmarkNotFoundEmergencyRoomIdExceptopn() {
        super(BookmarkErrorCode.BOOKMARK_NOT_FOUND_EMERGENCY_ROOM_ID);
    }
}
