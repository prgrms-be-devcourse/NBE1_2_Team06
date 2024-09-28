package com.nbe2.domain.posts.service.component;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.global.util.PagingUtil;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.repository.PostRepository;
import com.nbe2.domain.posts.service.dto.LocalPostPageCommand;
import com.nbe2.domain.posts.service.dto.PostCommand;

@Component
@RequiredArgsConstructor
public class PostReader {
    private final PostRepository postRepository;

    public PageResult<PostCommand> getPageByCity(final LocalPostPageCommand command) {
        Page<Post> postPage =
                postRepository.findByCity(command.city(), PagingUtil.toPageRequest(command.page()));
        List<PostCommand> postCommands =
                postPage.getContent().stream()
                        .map(
                                p ->
                                        new PostCommand(
                                                p.getId(),
                                                p.getUser().getName(),
                                                p.getTitle(),
                                                p.getContent()))
                        .toList();
        return new PageResult<>(postCommands, postPage.getTotalPages(), postPage.hasNext());
    }
}
