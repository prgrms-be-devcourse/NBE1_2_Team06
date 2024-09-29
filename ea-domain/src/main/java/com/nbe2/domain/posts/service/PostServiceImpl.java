package com.nbe2.domain.posts.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.posts.service.component.PostAppender;
import com.nbe2.domain.posts.service.component.PostReader;
import com.nbe2.domain.posts.service.dto.LocalPostPageCommand;
import com.nbe2.domain.posts.service.dto.PostDetailsCommand;
import com.nbe2.domain.posts.service.dto.PostListCommand;
import com.nbe2.domain.posts.service.dto.PostRegisterCommand;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostAppender postAppender;
    private final PostReader postReader;

    @Override
    @Transactional
    public Long savePost(final PostRegisterCommand command) {
        return postAppender.append(command);
    }

    @Override
    public PageResult<PostListCommand> findPostListPageByCity(final LocalPostPageCommand command) {
        return postReader.readPostListPageByCity(command);
    }

    @Override
    public PostDetailsCommand findPostDetails(Long postsId) {
        return postReader.readPostDetails(postsId);
    }
}
