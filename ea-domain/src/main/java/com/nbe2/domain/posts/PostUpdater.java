package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostUpdater {

    public Long update(final Post post, final PostWriteInfo info) {
        return post.update(info.title(), info.content(), info.city());
    }
}
