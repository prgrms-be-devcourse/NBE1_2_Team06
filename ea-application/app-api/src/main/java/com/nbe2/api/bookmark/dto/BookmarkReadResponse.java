package com.nbe2.api.bookmark.dto;

import java.util.List;

import com.nbe2.domain.bookmark.BookmarkReadInfo;

public record BookmarkReadResponse(List<Long> emergencyRoomIds) {
    public static BookmarkReadResponse from(BookmarkReadInfo bookmarkReadInfo) {
        return new BookmarkReadResponse(bookmarkReadInfo.emergencyRoomIds());
    }
}
