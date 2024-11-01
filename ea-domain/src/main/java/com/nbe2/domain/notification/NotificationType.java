package com.nbe2.domain.notification;

import lombok.Getter;

@Getter
public enum NotificationType {
    COMMENT("comment"),
    NOTICE("notice");

    private final String channelId;

    NotificationType(String channelId) {
        this.channelId = channelId;
    }
}
