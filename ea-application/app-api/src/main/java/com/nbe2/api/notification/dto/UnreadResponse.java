package com.nbe2.api.notification.dto;

public record UnreadResponse(boolean hasUnread) {

    public static UnreadResponse of(boolean hasUnread) {
        return new UnreadResponse(hasUnread);
    }
}
