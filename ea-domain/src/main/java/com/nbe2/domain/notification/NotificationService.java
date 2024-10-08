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

    private final NotificationAppender notificationAppender;
    private final NotificationReader notificationReader;

    public void sendNotification(CommentEvent event) {
        notificationAppender.append(event);
    }

    @Transactional(readOnly = true)
    public CursorResult<NotificationDetail> getNotificationHistory(Long userId, Cursor cursor) {
        List<NotificationDetail> notifications =
                notificationReader.read(userId, cursor).stream()
                        .map(NotificationDetail::from)
                        .toList();
        Long nextCursor =
                notificationReader.getNextCursor(userId, notifications.getLast().notificationId());
        return new CursorResult<>(notifications, nextCursor);
    }
}
