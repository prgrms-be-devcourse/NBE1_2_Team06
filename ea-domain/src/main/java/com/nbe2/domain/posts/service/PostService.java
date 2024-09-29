package com.nbe2.domain.posts.service;

import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.posts.service.dto.*;

public interface PostService {

    Long savePost(PostRegisterCommand command);

    PageResult<PostListCommand> findPostListPageByCity(LocalPostPageCommand command);

    PostDetailsCommand findPostDetails(Long postsId);

    Long updatePost(PostUpdateCommand command);
}
