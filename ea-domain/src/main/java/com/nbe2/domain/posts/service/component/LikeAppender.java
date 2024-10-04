package com.nbe2.domain.posts.service.component;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Like;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.repository.LikeRepository;
import com.nbe2.domain.user.User;

@Component
@RequiredArgsConstructor
public class LikeAppender {
    private final LikeRepository likeRepository;

    public void append(final Post post, final User user) {
        likeRepository.save(Like.create(post, user));
    }
}
