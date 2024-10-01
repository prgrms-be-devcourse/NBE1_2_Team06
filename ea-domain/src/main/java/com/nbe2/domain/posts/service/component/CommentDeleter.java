package com.nbe2.domain.posts.service.component;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.nbe2.domain.posts.entity.Comment;
import com.nbe2.domain.posts.repository.CommentRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommentDeleter {
    private final CommentRepository commentRepository;

    public Long delete(final Comment comment) {
        commentRepository.delete(comment);
        return comment.getId();
    }
}
