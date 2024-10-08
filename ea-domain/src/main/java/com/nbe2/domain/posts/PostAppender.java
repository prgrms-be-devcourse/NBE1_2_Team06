package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostAppender {
    private final PostRepository postRepository;

    public Long append(final Post post) {
        Post savePost = postRepository.save(post);
        return savePost.getId();
    }
}
