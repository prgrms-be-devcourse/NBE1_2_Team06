package com.nbe2.domain.notification;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentEvent {
    private static final int POST_TITLE_LENGTH_LIMIT = 10;
    @Getter private Long ownerId;
    private String postTitle;

    public static CommentEvent of(Long ownerId, String postTitle) {
        return new CommentEvent(ownerId, postTitle);
    }

    public String getPostTitle() {
        if (postTitle.length() <= POST_TITLE_LENGTH_LIMIT) return postTitle;
        return postTitle.substring(0, POST_TITLE_LENGTH_LIMIT) + "....";
    }
}
