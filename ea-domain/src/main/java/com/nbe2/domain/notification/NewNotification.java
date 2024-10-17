package com.nbe2.domain.notification;

public record NewNotification(
        long targetId, String referenceUri, String title, NotificationType type) {

    public static NewNotification of(
            long targetId, String referenceUri, String title, NotificationType type) {
        return new NewNotification(targetId, referenceUri, title, type);
    }
}
