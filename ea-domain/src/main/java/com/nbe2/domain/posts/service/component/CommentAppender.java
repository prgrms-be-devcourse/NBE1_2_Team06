package com.nbe2.domain.posts.service.component;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Comment;
import com.nbe2.domain.posts.exception.CommentRegisterConflictException;
import com.nbe2.domain.posts.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class CommentAppender {
    private final CommentRepository commentRepository;

    public void append(Comment comment) {
        comment.getPost().addComment();
        try {
            commentRepository.save(comment);
        } catch (CommentRegisterConflictException e) {
            throw CommentRegisterConflictException.EXCEPTION;
        }
    }
}
