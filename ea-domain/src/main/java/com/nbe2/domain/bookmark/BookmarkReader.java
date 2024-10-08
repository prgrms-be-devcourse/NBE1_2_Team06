package com.nbe2.domain.bookmark;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookmarkReader {
    private final BookmarkRepository bookmarkRepository;

    public List<Bookmark> readByUserId(Long userId) {
        return bookmarkRepository.findByUserId(userId);
    }

    public Bookmark readByEmergencyRoomId(Long emergencyRoomId) {
        return bookmarkRepository.findByEmergencyRoomId(emergencyRoomId);
    }
}
