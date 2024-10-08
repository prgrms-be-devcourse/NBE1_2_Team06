package com.nbe2.domain.notification;

public record NotificationDetail(
        Long notificationId, Long refId, String title, NotificationType type) {

    public static NotificationDetail from(Notification notification) {
        return new NotificationDetail(
                notification.getId(),
                notification.getRefId(),
                notification.getTitle(),
                notification.getType());
    }
}
