package com.nbe2.domain.posts.service;

import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.posts.service.dto.LocalPostPageCommand;
import com.nbe2.domain.posts.service.dto.PostListCommand;
import com.nbe2.domain.posts.service.dto.PostRegisterCommand;

public interface PostService {

    Long savePost(PostRegisterCommand command);

    PageResult<PostListCommand> findPostListPageByCity(LocalPostPageCommand command);
}
