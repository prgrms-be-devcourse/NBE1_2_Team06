package com.nbe2.api.notification.dto;

import com.nbe2.domain.notification.NotificationDetail;

public record NotificationDetailResponse(
        Long notificationId, Long refId, String title, String type) {

    public static NotificationDetailResponse from(NotificationDetail detail) {
        return new NotificationDetailResponse(
                detail.notificationId(), detail.refId(), detail.title(), detail.type().name());
    }
}
