package com.nbe2.domain.notification;

public record NotificationDetail(
        Long notificationId, String referenceUri, String title, NotificationType type) {

    public static NotificationDetail from(Notification notification) {
        return new NotificationDetail(
                notification.getId(),
                notification.getReferenceUri(),
                notification.getTitle(),
                notification.getType());
    }
}
