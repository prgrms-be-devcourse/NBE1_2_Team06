package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostUpdater {
    private final PostFileRegisterer postFileRegisterer;

    public Long update(final Post post, final PostWriteInfo info) {
        postFileRegisterer.register(post, info.fileIdList());
        Post updated = post.update(info.title(), info.content(), info.city());
        return updated.getId();
    }
}
