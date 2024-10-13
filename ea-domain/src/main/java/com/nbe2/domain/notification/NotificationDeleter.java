package com.nbe2.domain.notification;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationDeleter {

    public static final int OUTDATED_CRITERIA = 3; // 3 days

    private final NotificationRepository notificationRepository;

    public void deleteOutdatedNotifications() {
        notificationRepository.removeByCreatedAtBefore(
                LocalDateTime.now().minusDays(OUTDATED_CRITERIA));
    }
}
