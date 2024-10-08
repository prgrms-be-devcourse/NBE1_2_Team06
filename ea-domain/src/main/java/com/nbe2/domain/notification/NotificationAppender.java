package com.nbe2.domain.notification;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationAppender {

    private final NotificationRepository notificationRepository;

    public void append(CommentEvent commentEvent) {
        Notification notification =
                Notification.of(
                        commentEvent.getOwner(),
                        commentEvent.getPostTitle(),
                        NotificationType.COMMENT);
        notificationRepository.save(notification);
    }
}
