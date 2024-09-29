package com.nbe2.domain.posts.service.component;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.repository.PostRepository;

@Component
@RequiredArgsConstructor
public class PostAppender {
    private final PostRepository postRepository;

    public Long append(final Post post) {
        Post savePost = postRepository.save(post);
        return savePost.getId();
    }
}
