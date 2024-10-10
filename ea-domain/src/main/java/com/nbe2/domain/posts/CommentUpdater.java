package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

@Component
public class CommentUpdater {

    public Long update(final Comment comment, final CommentInfo info) {
        return comment.update(info.content());
    }
}
