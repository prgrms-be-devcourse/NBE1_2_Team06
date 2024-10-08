package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.nbe2.domain.posts.exception.PostNotFoundException;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostDeleter {
    private final PostRepository postRepository;

    public void delete(final Long postsId) {
        Post post =
                postRepository.findById(postsId).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        postRepository.delete(post);
    }
}
