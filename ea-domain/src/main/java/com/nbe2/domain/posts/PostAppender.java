package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostAppender {
    private final PostRepository postRepository;

    public Long append(final Post post) {
        Post saved = postRepository.save(post);
        return saved.getId();
    }
}
