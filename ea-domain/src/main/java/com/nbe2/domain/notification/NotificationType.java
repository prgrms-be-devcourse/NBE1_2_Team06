package com.nbe2.domain.notification;

import lombok.Getter;

@Getter
public enum NotificationType {
    COMMENT("comment");

    private final String channelId;

    NotificationType(String channelId) {
        this.channelId = channelId;
    }
}
