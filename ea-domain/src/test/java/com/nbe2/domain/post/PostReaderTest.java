package com.nbe2.domain.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.posts.*;
import com.nbe2.domain.posts.exception.PostNotFoundException;

@ExtendWith(MockitoExtension.class)
class PostReaderTest {

    @InjectMocks PostReader postReader;

    @Mock PostRepository postRepository;

    @Test
    @DisplayName("유효한 게시글 PK를 이용하여 게시글을 조회한다.")
    void givenPostId_whenFind_thenShouldReturnPost() {
        // given
        Long postId = 1L;
        Post expected = PostFixture.createPostWithId(postId);
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        // when
        Post actual = postReader.read(postId);

        // then
        verify(postRepository).findById(postId);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("존재하지 않는 게시글을 조회시 예외를 반환한다.")
    void givenPostId_whenNotExists_thenShouldThrowException() {
        // given
        Long postId = 1L;
        when(postRepository.findById(anyLong())).thenThrow(PostNotFoundException.EXCEPTION);

        // when

        // then
        assertThrows(PostNotFoundException.class, () -> postReader.read(postId));
    }

    @Test
    @DisplayName("PostRepository의 findDetailById를 이용하여 게시글을 조회한다.")
    void givenPostId_whenReadDetail_thenShouldExecuteFindDetailById() {
        // given
        Long postId = 1L;
        Post expected = PostFixture.createPostWithId(postId);
        when(postRepository.findDetailById(anyLong())).thenReturn(Optional.of(expected));

        // when
        Post actual = postReader.readDetail(postId);

        // then
        verify(postRepository).findDetailById(postId);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    @DisplayName("PostRepository의 findByIdWithPessimisticWriteLock를 이용하여 게시글을 조회한다.")
    void
            givenPostId_whenReadWithPessimisticWriteLock_thenShouldExecuteFindByIdWithPessimisticWriteLock() {
        // given
        Long postId = 1L;
        Post expected = PostFixture.createPostWithId(postId);
        when(postRepository.findByIdWithPessimisticWriteLock(anyLong()))
                .thenReturn(Optional.of(expected));

        // when
        Post actual = postReader.readWithPessimisticWriteLock(postId);

        // then
        verify(postRepository).findByIdWithPessimisticWriteLock(postId);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    @DisplayName("페이지요청정보와 도시를 이용하여 게시글페이지를 조회한다.")
    void readListPage() {
        // given
        City city = City.ANDONG;
        PageRequest pageRequest = PageRequest.of(0, 10);
        Post post1 = PostFixture.createPostWithId(1L);
        Post post2 = PostFixture.createPostWithId(2L);
        List<Post> postList = List.of(post1, post2);
        Page<Post> postPage = new PageImpl<>(postList, pageRequest, 1);
        List<PostListInfo> postListInfoList =
                postList.stream()
                        .map(
                                post ->
                                        new PostListInfo(
                                                post.getId(),
                                                post.getWriterName(),
                                                post.getWriterName(),
                                                post.getContent(),
                                                post.getLikeCount(),
                                                post.getCommentCount()))
                        .toList();
        PageResult<PostListInfo> expect =
                PageResult.of(postListInfoList, postPage.getTotalPages(), postPage.hasNext());
        when(postRepository.findByCity(any(City.class), any(PageRequest.class)))
                .thenReturn(postPage);

        // when
        PageResult<PostListInfo> actual = postReader.readListPage(pageRequest, city);

        // then
        verify(postRepository).findByCity(city, pageRequest);
        assertEquals(expect.content().size(), actual.content().size());
    }
}
