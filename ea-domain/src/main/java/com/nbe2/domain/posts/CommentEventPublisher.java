package com.nbe2.domain.posts;

public interface CommentEventPublisher {

    void publish(NewCommentEvent event);
}
