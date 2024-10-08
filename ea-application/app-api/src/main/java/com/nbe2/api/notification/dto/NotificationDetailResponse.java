package com.nbe2.api.notification.dto;

import com.nbe2.domain.notification.NotificationDetail;

public record NotificationDetailResponse(
        Long notificationId, String referenceUri, String title, String type) {

    public static NotificationDetailResponse from(NotificationDetail detail) {
        return new NotificationDetailResponse(
                detail.notificationId(),
                detail.referenceUri(),
                detail.title(),
                detail.type().name());
    }
}
