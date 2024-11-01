package com.nbe2.domain.posts;

public record NewCommentEvent(long targetId, String referenceUri, String postTitle) {

    public static NewCommentEvent from(Post post) {
        return new NewCommentEvent(
                post.getUser().getId(), "/posts/" + post.getId(), post.getTitle());
    }
}
