package com.nbe2.domain.bookmark;

import java.util.ArrayList;
import java.util.List;

public record BookmarkReadInfo(List<Long> emergencyRoomIds) {

    public static BookmarkReadInfo from(List<Bookmark> bookmarks) {
        List<Long> emergencyRoomIds = new ArrayList<>();
        for (Bookmark bookmark : bookmarks) {
            emergencyRoomIds.add(bookmark.getEmergencyRoom().getId());
        }
        return new BookmarkReadInfo(emergencyRoomIds);
    }
}
