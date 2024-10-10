package com.nbe2.domain.notification;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.Cursor;
import com.nbe2.common.dto.CursorResult;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationUpdater notificationUpdater;
    private final NotificationReader notificationReader;

    @Transactional
    public CursorResult<NotificationDetail> getNotificationHistory(Long userId, Cursor cursor) {
        List<NotificationDetail> notifications = notificationReader.read(userId, cursor);
        Long nextCursor = getNextCursor(userId, notifications);
        notificationUpdater.readAllUnreadNotifications(userId);
        return new CursorResult<>(notifications, nextCursor);
    }

    public boolean hasUnreadNotification(Long userId) {
        return notificationReader.hasUnreadNotification(userId);
    }

    private Long getNextCursor(Long userId, List<NotificationDetail> notifications) {
        if (notifications.isEmpty()) return null;

        return notificationReader.getNextCursor(
                userId, notifications.get(notifications.size() - 1).notificationId());
    }
}
