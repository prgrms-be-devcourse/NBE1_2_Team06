package com.nbe2.domain.notification;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.UserReader;

@Component
@RequiredArgsConstructor
public class NotificationAppender {

    private final UserReader userReader;
    private final NotificationRepository notificationRepository;

    public void append(NotificationEvent event) {
        Notification notification =
                Notification.of(
                        userReader.read(event.targetId()),
                        event.referenceUri(),
                        event.title(),
                        event.notificationType());
        notificationRepository.save(notification);
    }
}
