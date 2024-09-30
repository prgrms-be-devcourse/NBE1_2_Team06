package com.nbe2.domain.posts.service.component;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.service.dto.PostDefaultInfo;

@Component
@RequiredArgsConstructor
public class PostUpdater {

    public Long update(final Post post, final PostDefaultInfo info) {
        return post.update(info.title(), info.content(), info.city());
    }
}
