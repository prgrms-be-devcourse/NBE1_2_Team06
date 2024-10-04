package com.nbe2.domain.posts.service;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.service.component.LikeAppender;
import com.nbe2.domain.posts.service.component.LikeDeleter;
import com.nbe2.domain.posts.service.component.LikeValidator;
import com.nbe2.domain.posts.service.component.PostReader;
import com.nbe2.domain.posts.service.dto.LikeInfo;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final PostReader postReader;
    private final UserReader userReader;
    private final LikeAppender likeAppender;
    private final LikeDeleter likeDeleter;
    private final LikeValidator likeValidator;

    @Transactional
    public void addLike(final LikeInfo info) {
        Post post = postReader.readWithPessimisticWriteLock(info.postId());
        User user = userReader.read(info.userId());
        if (likeValidator.isExist(post, user)) return;
        likeAppender.append(post, user);
        post.increaseLikeCount();
    }

    @Transactional
    public void cancelLike(final LikeInfo info) {
        Post post = postReader.readWithPessimisticWriteLock(info.postId());
        User user = userReader.read(info.userId());
        if (likeValidator.isExist(post, user)) {
            likeDeleter.delete(post, user);
            post.decreaseLikeCount();
        }
    }
}
