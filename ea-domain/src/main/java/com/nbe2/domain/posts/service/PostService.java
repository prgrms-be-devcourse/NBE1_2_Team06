package com.nbe2.domain.posts.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.global.util.PagingUtil;
import com.nbe2.domain.posts.entity.City;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.service.component.PostAppender;
import com.nbe2.domain.posts.service.component.PostDeleter;
import com.nbe2.domain.posts.service.component.PostReader;
import com.nbe2.domain.posts.service.component.PostUpdater;
import com.nbe2.domain.posts.service.dto.*;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostAppender postAppender;
    private final PostReader postReader;
    private final PostUpdater postUpdater;
    private final PostDeleter postDeleter;
    private final UserReader userReader;

    @Transactional
    public Long save(final Long userId, final PostDefaultInfo info) {
        User user = userReader.read(userId);
        return postAppender.append(info.toEntity(user));
    }

    public PageResult<PostListInfo> findListPageByCity(final Page page, final City city) {
        return postReader.readListPage(PagingUtil.toPageRequest(page), city);
    }

    public PageResult<PostListInfo> findListPageByUserId(final Page page, final Long userId) {
        User user = userReader.read(userId);
        return postReader.readListPage(PagingUtil.toPageRequest(page), user);
    }

    public PostDetailsInfo findDetails(final Long postsId) {
        Post post = postReader.read(postsId);
        return PostDetailsInfo.from(post);
    }

    @Transactional
    public Long update(final Long postsId, final PostDefaultInfo info) {
        Post post = postReader.read(postsId);
        return postUpdater.update(post, info);
    }

    @Transactional
    public void delete(final Long postsId) {
        postDeleter.delete(postsId);
    }
}
