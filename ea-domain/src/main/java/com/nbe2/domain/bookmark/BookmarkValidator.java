package com.nbe2.domain.bookmark;

import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.bookmark.exception.BookmarkNotFoundEmergencyRoomIdException;
import com.nbe2.domain.bookmark.exception.BookmarkNotYoursException;

@Component
@RequiredArgsConstructor
public class BookmarkValidator {

    public Bookmark validateBookmark(Optional<Bookmark> bookmark) {
        bookmark.orElseThrow(() -> BookmarkNotFoundEmergencyRoomIdException.EXCEPTOPN);
        return bookmark.get();
    }

    public void validateUser(Long userId, Bookmark bookmark) {
        // 유저가 추가한 즐겨찾기가 맞는지 검증
        if (!isYourBookmark(userId, bookmark)) {
            throw BookmarkNotYoursException.EXCEPTOPN;
        }
    }

    private boolean isYourBookmark(Long userId, Bookmark bookmark) {
        if (!userId.equals(bookmark.getUser().getId())) {
            return false;
        }
        return true;
    }
}
