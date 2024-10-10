package com.nbe2.domain.notification;

import com.nbe2.domain.posts.Post;

public record NotificationEvent(
        long targetId, String referenceUri, String title, NotificationType notificationType) {

    public static NotificationEvent from(Post post) {
        return new NotificationEvent(
                post.getUser().getId(),
                "/posts/" + post.getId(),
                post.getTitle(),
                NotificationType.COMMENT);
    }
}
