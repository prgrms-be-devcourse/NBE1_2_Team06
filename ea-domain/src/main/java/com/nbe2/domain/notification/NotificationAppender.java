package com.nbe2.domain.notification;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.UserReader;

@Component
@RequiredArgsConstructor
public class NotificationAppender {

    private final UserReader userReader;
    private final NotificationRepository notificationRepository;

    public void append(NewNotification notification) {
        notificationRepository.save(
                Notification.of(
                        userReader.read(notification.targetId()),
                        notification.referenceUri(),
                        notification.title(),
                        notification.type()));
    }
}
