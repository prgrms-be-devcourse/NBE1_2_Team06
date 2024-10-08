package com.nbe2.domain.notification;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.Cursor;

@Component
@RequiredArgsConstructor
public class NotificationReader {

    private final NotificationRepository notificationRepository;

    public List<Notification> read(Long userId, Cursor cursor) {
        return notificationRepository.findByUserIdWithCursor(
                userId, cursor.cursor(), cursor.size());
    }

    public Long getNextCursor(Long userId, Long lastCursor) {
        return notificationRepository.findNextCursor(userId, lastCursor);
    }
}
