package com.nbe2.domain.posts.service.component;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.posts.entity.City;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.exception.PostNotFoundException;
import com.nbe2.domain.posts.repository.PostRepository;
import com.nbe2.domain.posts.service.dto.PostListInfo;
import com.nbe2.domain.user.User;

@Component
@RequiredArgsConstructor
public class PostReader {
    private final PostRepository postRepository;

    public Post read(Long id) {
        return postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }

    public Post readWithPessimisticWriteLock(Long id) {
        return postRepository
                .findByIdWithPessimisticWriteLock(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }

    public PageResult<PostListInfo> readListPage(PageRequest pageRequest, City city) {
        Page<Post> postPage = postRepository.findByCity(city, pageRequest);
        return new PageResult<>(mapToInfo(postPage), postPage.getTotalPages(), postPage.hasNext());
    }

    public PageResult<PostListInfo> readListPage(PageRequest pageRequest, User user) {
        Page<Post> postPage = postRepository.findByUser(user, pageRequest);
        return new PageResult<>(mapToInfo(postPage), postPage.getTotalPages(), postPage.hasNext());
    }

    private List<PostListInfo> mapToInfo(Page<Post> postPage) {
        return postPage.getContent().stream()
                .map(
                        post ->
                                new PostListInfo(
                                        post.getId(),
                                        post.getWriterName(),
                                        post.getTitle(),
                                        post.getContent(),
                                        post.getLikeCount(),
                                        post.getCommentCount()))
                .toList();
    }
}
