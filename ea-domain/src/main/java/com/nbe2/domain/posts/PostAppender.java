package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.User;

@Component
@RequiredArgsConstructor
public class PostAppender {
    private final PostFileSetter postFileSetter;
    private final PostRepository postRepository;

    public Long append(final User user, final PostWriteInfo info) {
        Post newPost = Post.create(user, info.title(), info.content(), info.city());
        postFileSetter.set(newPost, info.fileIdList());
        Post saved = postRepository.save(newPost);
        return saved.getId();
    }
}
