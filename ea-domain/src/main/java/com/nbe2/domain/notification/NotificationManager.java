package com.nbe2.domain.notification;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationManager {

    private final NotificationAppender notificationAppender;
    private final NotificationSender notificationSender;

    public void send(NewNotification notification) {
        notificationAppender.append(notification);
        notificationSender.send(
                NewNotificationEvent.of(notification.targetId(), notification.type()));
    }
}
