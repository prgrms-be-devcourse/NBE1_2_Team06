package com.nbe2.domain.notification;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.Cursor;
import com.nbe2.common.dto.CursorResult;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationEventPublisher notificationEventPublisher;
    private final NotificationReader notificationReader;

    public CursorResult<NotificationDetail> getNotificationHistory(Long userId, Cursor cursor) {
        List<NotificationDetail> notifications = notificationReader.read(userId, cursor);
        Long nextCursor =
                notificationReader.getNextCursor(userId, notifications.getLast().notificationId());
        notificationEventPublisher.publish(userId);
        return new CursorResult<>(notifications, nextCursor);
    }

    public boolean hasUnreadNotification(Long userId) {
        return notificationReader.hasUnreadNotification(userId);
    }
}
