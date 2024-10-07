package com.nbe2.domain.notification;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationAppender notificationAppender;

    public void sendNotification(CommentEvent event) {
        notificationAppender.append(event);
    }
}
