package com.nbe2.infra.notification.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notification.NotificationDeleter;

@Component
@RequiredArgsConstructor
public class NotificationScheduler {

    private final NotificationDeleter notificationDeleter;

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteOutdatedNotifications() {
        notificationDeleter.deleteOutdatedNotifications();
    }
}
