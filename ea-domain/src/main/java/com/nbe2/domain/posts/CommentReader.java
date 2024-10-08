package com.nbe2.domain.posts;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.exception.CommentNotFoundException;

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
        return commentRepository.findByPostId(post.getId());
    }
}
