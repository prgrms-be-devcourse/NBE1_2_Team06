package com.nbe2.domain.posts.service.component;

import org.springframework.stereotype.Component;

import com.nbe2.domain.posts.entity.Comment;
import com.nbe2.domain.posts.service.dto.CommentDefaultInfo;

@Component
public class CommentUpdater {

    public Long update(final Comment comment, final CommentDefaultInfo info) {
        return comment.update(info.content());
    }
}
