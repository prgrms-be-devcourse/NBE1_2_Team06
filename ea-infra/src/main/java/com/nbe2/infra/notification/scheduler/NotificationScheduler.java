package com.nbe2.infra.notification.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.nbe2.domain.notification.NotificationDeleter;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationScheduler {

    private final NotificationDeleter notificationDeleter;

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    @SchedulerLock(name = "notification", lockAtLeastFor = "PT2S", lockAtMostFor = "PT2S")
    public void deleteOutdatedNotifications() {
        log.info("Deleting outdated notifications");
        notificationDeleter.deleteOutdatedNotifications();
    }
}
