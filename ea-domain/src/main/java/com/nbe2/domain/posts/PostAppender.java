package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.User;

@Component
@RequiredArgsConstructor
public class PostAppender {
    private final PostFileRegisterer postFileRegisterer;
    private final PostRepository postRepository;

    public Long append(final User user, final PostWriteInfo info) {
        Post newPost =
                postRepository.save(Post.create(user, info.title(), info.content(), info.city()));
        postFileRegisterer.register(newPost, info.fileIdList());
        return newPost.getId();
    }
}
