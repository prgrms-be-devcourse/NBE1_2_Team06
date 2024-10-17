package com.nbe2.domain.bookmark;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.bookmark.exception.BookmarkNotFoundEmergencyRoomIdException;

@Component
@RequiredArgsConstructor
public class BookmarkReader {

    private final BookmarkRepository bookmarkRepository;

    public List<Bookmark> readByUserId(Long userId) {
        return bookmarkRepository.findByUserId(userId);
    }

    public Bookmark readByEmergencyRoomIdAndUserId(Long emergencyRoomId, Long userId) {
        Bookmark bookmark =
                bookmarkRepository
                        .findByEmergencyRoomIdAndUserId(emergencyRoomId, userId)
                        .orElseThrow(() -> BookmarkNotFoundEmergencyRoomIdException.EXCEPTOPN);
        return bookmark;
    }

    public List<Long> readUserIdsByEmergencyRoomId(Long emergencyRoomId) {
        return bookmarkRepository.findUserIdsByEmergencyRoomId(emergencyRoomId);
    }
}
