package com.nbe2.domain.notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository
        extends JpaRepository<Notification, Long>, NotificationRepositoryCustom {

    boolean existsByTargetIdAndIsRead(long userId, boolean isRead);
}
