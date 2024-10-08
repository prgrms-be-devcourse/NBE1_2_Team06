package com.nbe2.domain.notification;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import com.nbe2.domain.posts.Post;
import com.nbe2.domain.user.User;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentEvent {
    public static final String EVENT_NAME = "COMMENT_NOTIFICATION";
    private static final int POST_TITLE_LENGTH_LIMIT = 10;

    @Getter private User owner;
    @Getter private Long postId;
    private String postTitle;

    public static CommentEvent from(Post post) {
        return new CommentEvent(post.getUser(), post.getId(), post.getTitle());
    }

    public String getPostTitle() {
        if (postTitle.length() <= POST_TITLE_LENGTH_LIMIT) return postTitle;
        return postTitle.substring(0, POST_TITLE_LENGTH_LIMIT) + "....";
    }
}
