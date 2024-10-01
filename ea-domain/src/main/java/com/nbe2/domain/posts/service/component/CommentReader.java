package com.nbe2.domain.posts.service.component;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Comment;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.exception.CommentNotFoundException;
import com.nbe2.domain.posts.repository.CommentRepository;
import com.nbe2.domain.user.User;

@Component
@RequiredArgsConstructor
public class CommentReader {
    private final CommentRepository commentRepository;

    public Comment read(final Long commentId) {
        return commentRepository
                .findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
    }

    public List<Comment> read(final Post post) {
        return commentRepository.findByPost(post);
    }

    public List<Comment> read(final User user) {
        return commentRepository.findByUser(user);
    }
}
