package com.nbe2.domain.notification;

import lombok.Getter;

@Getter
public enum NotificationType {
    COMMENT("comment"),
    NOTICE("notice");

    public static final String CHANNEL_ID = "notification";
    private final String channelId;

    NotificationType(String channelId) {
        this.channelId = channelId;
    }
}
