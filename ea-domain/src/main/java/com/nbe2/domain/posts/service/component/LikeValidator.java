package com.nbe2.domain.posts.service.component;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.repository.LikeRepository;
import com.nbe2.domain.user.User;

@Component
@RequiredArgsConstructor
public class LikeValidator {
    private final LikeRepository likeRepository;

    public boolean isExist(final Post post, final User user) {
        return likeRepository.findByPostAndUser(post, user).isPresent();
    }
}
