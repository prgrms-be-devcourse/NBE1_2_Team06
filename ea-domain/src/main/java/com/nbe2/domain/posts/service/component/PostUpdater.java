package com.nbe2.domain.posts.service.component;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.exception.PostNotFoundException;
import com.nbe2.domain.posts.repository.PostRepository;
import com.nbe2.domain.posts.service.dto.PostUpdateCommand;

@Component
@RequiredArgsConstructor
public class PostUpdater {
    private final PostRepository postRepository;

    public Long postUpdate(PostUpdateCommand command) {
        Post post =
                postRepository
                        .findById(command.id())
                        .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        return post.update(command.title(), command.content(), command.city());
    }
}
