package com.nbe2.domain.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.global.util.PagingUtil;
import com.nbe2.domain.posts.*;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;
import com.nbe2.domain.user.UserReader;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks PostService postService;
    @Mock PostAppender postAppender;
    @Mock PostReader postReader;
    @Mock PostUpdater postUpdater;
    @Mock PostDeleter postDeleter;
    @Mock UserReader userReader;

    @Test
    @DisplayName("회원PK와 게시글작성정보를 이용하여 게시글을 저장한다.")
    public void givenUserIdPostWriteInfo_whenSave_thenShouldReturnPostId() throws Exception {
        // given
        Long userId = 1L;
        PostWriteInfo postWriteInfo =
                new PostWriteInfo("title", "content", City.ANDONG, Optional.empty());
        User user = UserFixture.createUser();
        ReflectionTestUtils.setField(user, "id", userId);
        when(userReader.read(userId)).thenReturn(user);
        Long expect = 1L;
        when(postAppender.append(any(User.class), any(PostWriteInfo.class))).thenReturn(expect);

        // when
        Long actual = postService.save(userId, postWriteInfo);

        // then
        verify(userReader).read(userId);
        verify(postAppender).append(user, postWriteInfo);
        assertEquals(expect, actual);
    }

    @Test
    @DisplayName("페이지요청정보와 지역을 이용하여 페이지정보를 조회한다.")
    public void givenPageCity_whenFindListPageByCity_thenShouldReturnPageResult() throws Exception {
        // given
        Page page = new Page(1, 10);
        City city = City.ANDONG;
        PageResult<PostListInfo> expect =
                new PageResult<>(
                        List.of(new PostListInfo(1L, "username", "title", "content", 0L, 0L)),
                        1,
                        false);
        when(postReader.readListPage(any(PageRequest.class), any(City.class))).thenReturn(expect);

        // when
        PageResult<PostListInfo> actual = postService.findListPageByCity(page, city);

        // then
        verify(postReader).readListPage(PagingUtil.toPageRequest(page), city);
        assertEquals(expect.content().size(), actual.content().size());
    }

    @Test
    @DisplayName("페이지요청정보와 회원PK를 이용하여 페이지정보를 조회한다.")
    public void givenPageUserId_whenGetUserPostPages_thenShouldReturnPageResult() throws Exception {
        // given
        Page page = new Page(1, 10);
        Long userId = 1L;
        User user = UserFixture.createUser();
        ReflectionTestUtils.setField(user, "id", userId);
        when(userReader.read(userId)).thenReturn(user);
        PageResult<PostListInfo> expect =
                new PageResult<>(
                        List.of(new PostListInfo(1L, "username", "title", "content", 0L, 0L)),
                        1,
                        false);
        when(postReader.readListPage(any(PageRequest.class), any(User.class))).thenReturn(expect);

        // when
        PageResult<PostListInfo> actual = postService.getUserPostPages(page, userId);

        // then
        verify(postReader).readListPage(PagingUtil.toPageRequest(page), user);
        assertEquals(expect.content().size(), actual.content().size());
    }

    @Test
    @DisplayName("게시글PK를 이용하여 게시글상세정보를 조회한다.")
    public void givenPostId_whenFindDetails_thenShouldReturnPostDetailsInfo() throws Exception {
        // given
        Long postId = 1L;
        Post post = PostFixture.createPostWithId(postId);
        when(postReader.readDetail(anyLong())).thenReturn(post);
        PostDetailsInfo expect = PostDetailsInfo.from(post);

        // when
        PostDetailsInfo actual = postService.findDetails(postId);

        // then
        verify(postReader).readDetail(postId);
        assertEquals(expect.id(), actual.id());
    }

    @Test
    @DisplayName("게시글PK와 게시글작성정보를 이용하여 게시글을 수정한다.")
    public void givenPostIdPostWriteInfo_whenUpdate_thenShouldReturnPostId() throws Exception {
        // given
        Long postId = 1L;
        PostWriteInfo postWriteInfo =
                PostWriteInfo.create("title", "content", City.ANDONG, Optional.empty());
        Post post = PostFixture.createPostWithId(postId);
        when(postReader.read(anyLong())).thenReturn(post);
        Long expect = 1L;
        when(postUpdater.update(any(Post.class), any(PostWriteInfo.class))).thenReturn(expect);

        // when
        Long actual = postService.update(postId, postWriteInfo);

        // then
        verify(postReader).read(postId);
        verify(postUpdater).update(post, postWriteInfo);
        assertEquals(expect, actual);
    }

    @Test
    @DisplayName("게시글PK를 이용하여 게시글을 삭제한다.")
    public void givenPostId_whenDelete_thenShouldReturnNothing() throws Exception {
        // given
        Long postId = 1L;

        // when
        postService.delete(postId);

        // then
        verify(postDeleter).delete(postId);
    }
}
