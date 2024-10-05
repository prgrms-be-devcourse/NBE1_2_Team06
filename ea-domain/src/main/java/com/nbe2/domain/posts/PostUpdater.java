package com.nbe2.domain.posts;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostUpdater {
    private final PostRepository postRepository;
    private final PostFileSetter postFileSetter;

    public Long update(final Post post, final PostWriteInfo info) {
        Post updatedPost = post.update(info.title(), info.content(), info.city());
        Optional<List<Long>> fileIdList = info.fileIdList();
        fileIdList.ifPresent(list -> postFileSetter.set(post, list));

        Post saved = postRepository.save(updatedPost);
        return saved.getId();
    }
}
