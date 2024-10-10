package com.nbe2.domain.notification;

import com.nbe2.domain.posts.Post;

public record NotificationMessage(String channelId, NotificationEvent event) {

    public static NotificationMessage from(Post post) {
        NotificationEvent event = NotificationEvent.from(post);
        return new NotificationMessage(event.notificationType().getChannelId(), event);
    }
}
