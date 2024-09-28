package com.nbe2.domain.posts.service.component;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.repository.PostRepository;
import com.nbe2.domain.posts.service.dto.PostRegisterCommand;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserRepository;
import com.nbe2.domain.user.exception.UserNotFoundException;

@Component
@RequiredArgsConstructor
public class PostAppender {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long append(final PostRegisterCommand command) {
        User user =
                userRepository
                        .findByEmail(command.email())
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        Post post = postRepository.save(command.toEntity(user));
        return post.getId();
    }
}
