package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.User;

@Component
@RequiredArgsConstructor
public class LikeDeleter {
    private final LikeRepository likeRepository;

    public void delete(final Post post, final User user) {
        likeRepository.deleteByPostAndUser(post, user);
    }
}
