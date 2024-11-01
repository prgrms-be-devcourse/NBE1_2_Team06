package com.nbe2.domain.post;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.nbe2.domain.posts.*;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;
import com.nbe2.domain.user.UserReader;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @InjectMocks LikeService likeService;

    @Mock PostReader postReader;

    @Mock UserReader userReader;

    @Mock LikeAppender likeAppender;

    @Mock LikeDeleter likeDeleter;

    @Mock LikeValidator likeValidator;

    @Test
    @DisplayName("좋아요정보를 이용해 좋아요를 추가한다.")
    void givenLikeInfo_whenAddLike_thenShouldReturnNothing() {
        // given
        Long postId = 1L;
        Long userId = 1L;
        LikeInfo likeInfo = LikeInfo.of(postId, userId);
        Post post = PostFixture.createPostWithId(postId);
        when(postReader.readWithPessimisticWriteLock(anyLong())).thenReturn(post);
        User user = UserFixture.createUser();
        ReflectionTestUtils.setField(user, "id", userId);
        when(userReader.read(anyLong())).thenReturn(user);
        when(likeValidator.isExist(any(Post.class), any(User.class))).thenReturn(false);

        // when
        likeService.addLike(likeInfo);

        // then
        verify(postReader).readWithPessimisticWriteLock(likeInfo.postId());
        verify(userReader).read(likeInfo.userId());
        verify(likeValidator).isExist(post, user);
        verify(likeAppender).append(post, user);
    }

    @Test
    @DisplayName("좋아요정보를 이용해 좋아요를 취소한다.")
    void givenLikeInfo_whenCancelLike_thenShouldReturnNothing() {
        // given
        Long postId = 1L;
        Long userId = 1L;
        LikeInfo likeInfo = LikeInfo.of(postId, userId);
        Post post = PostFixture.createPostWithId(postId);
        when(postReader.readWithPessimisticWriteLock(anyLong())).thenReturn(post);
        User user = UserFixture.createUser();
        ReflectionTestUtils.setField(user, "id", userId);
        when(userReader.read(anyLong())).thenReturn(user);
        when(likeValidator.isExist(any(Post.class), any(User.class))).thenReturn(true);

        // when
        likeService.cancelLike(likeInfo);

        // then
        verify(postReader).readWithPessimisticWriteLock(likeInfo.postId());
        verify(userReader).read(likeInfo.userId());
        verify(likeValidator).isExist(post, user);
        verify(likeDeleter).delete(post, user);
    }
}
