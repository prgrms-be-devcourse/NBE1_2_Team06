package com.nbe2.domain.notification;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationManager {

    private final NotificationAppender notificationAppender;
    private final EventSender eventSender;

    public void sendCommentNotification(NotificationEvent event) {
        notificationAppender.append(event);
        eventSender.send(event);
    }
}
