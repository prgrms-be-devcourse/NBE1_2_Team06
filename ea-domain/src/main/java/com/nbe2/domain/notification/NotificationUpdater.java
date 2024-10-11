package com.nbe2.domain.notification;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationUpdater {

    private final NotificationRepository notificationRepository;

    public void readAllUnreadNotifications(long userId) {
        notificationRepository.setIsRead(userId, true);
    }
}
