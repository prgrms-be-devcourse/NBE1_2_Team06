package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.User;

@Component
@RequiredArgsConstructor
public class LikeAppender {
    private final LikeRepository likeRepository;

    public void append(final Post post, final User user) {
        likeRepository.save(Like.create(post, user));
    }
}
