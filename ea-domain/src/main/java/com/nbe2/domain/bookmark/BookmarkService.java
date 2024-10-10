package com.nbe2.domain.bookmark;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.UserReader;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkAppender bookmarkAppender;
    private final BookmarkReader bookmarkReader;
    private final BookmarkDeleter bookmarkDeleter;
    private final UserReader userReader;

    public void saveBookmark(Long emergencyRoomId, Long userId) {
        bookmarkAppender.save(emergencyRoomId, userId);
    }

    public BookmarkReadInfo readBookmark(Long userId) {
        List<Bookmark> bookmarks = bookmarkReader.readByUserId(userId);
        return BookmarkReadInfo.from(bookmarks);
    }

    public void deleteBookmark(Long emergencyRoomId, Long userId) {
        Bookmark bookmark = bookmarkReader.readByEmergencyRoomIdAndUserId(emergencyRoomId, userId);
        bookmarkDeleter.deleteBookmark(bookmark);
    }
}
