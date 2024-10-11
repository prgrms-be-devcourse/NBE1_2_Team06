package com.nbe2.domain.bookmark;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomReader;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Component
@RequiredArgsConstructor
public class BookmarkAppender {
    private final BookmarkRepository bookmarkRepository;
    private final UserReader userReader;
    private final EmergencyRoomReader emergencyRoomReader;

    public void save(Long emergencyRoomId, Long userId) {
        EmergencyRoom emergencyRoom = emergencyRoomReader.read(emergencyRoomId);
        User user = userReader.read(userId);
        Bookmark newBookmark = Bookmark.from(user, emergencyRoom);
        bookmarkRepository.save(newBookmark);
    }
}
