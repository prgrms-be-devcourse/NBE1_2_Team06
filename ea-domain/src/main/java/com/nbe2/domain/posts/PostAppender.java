package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostAppender {
    private final PostRepository postRepository;

    public Post append(final Post post) {
        return postRepository.save(post);
    }
}
