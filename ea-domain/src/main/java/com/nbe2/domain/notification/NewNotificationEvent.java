package com.nbe2.domain.notification;

public record NewNotificationEvent(long targetId, NotificationType type) {

    public static NewNotificationEvent of(long targetId, NotificationType type) {
        return new NewNotificationEvent(targetId, type);
    }
}
