package com.nbe2.domain.bookmark;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookmarkReader {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkValidator bookmarkValidator;

    public List<Bookmark> readByUserId(Long userId) {
        return bookmarkRepository.findByUserId(userId);
    }

    public Bookmark readByEmergencyRoomId(Long emergencyRoomId) {
        Bookmark bookmark =
                bookmarkValidator.validateBookmark(
                        bookmarkRepository.findByEmergencyRoomId(emergencyRoomId));
        return bookmark;
    }
}
