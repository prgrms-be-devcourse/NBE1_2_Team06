package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostUpdater {
    private final PostFileSetter postFileSetter;

    public Long update(final Post post, final PostWriteInfo info) {
        Post updated = post.update(info.title(), info.content(), info.city());
        postFileSetter.set(updated, info.fileIdList());
        return updated.getId();
    }
}
