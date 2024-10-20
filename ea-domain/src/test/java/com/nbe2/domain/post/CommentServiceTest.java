package com.nbe2.domain.post;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.nbe2.domain.posts.*;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @InjectMocks CommentService commentService;

    @Mock UserReader userReader;
    @Mock PostReader postReader;
    @Mock CommentAppender commentAppender;
    @Mock CommentReader commentReader;
    @Mock CommentUpdater commentUpdater;
    @Mock CommentDeleter commentDeleter;
    @Mock CommentValidator commentValidator;
    @Mock CommentEventPublisher eventPublisher;

    @Test
    @DisplayName("게시글PK와 댓글작성정보를 이용하여 댓글을 등록한다.")
    public void givenPostIdCommentWriteInfo_whenSave_thenShouldReturnPostId() throws Exception {
        // given
        Long postId = 1L;
        Long userId = 1L;
        CommentWriteInfo commentWriteInfo =
                CommentWriteInfo.create(userId, CommentInfo.of("content"));
        Post post = PostFixture.createPostWithId(postId);
        when(postReader.readWithPessimisticWriteLock(anyLong())).thenReturn(post);
        User user = post.getUser();
        ReflectionTestUtils.setField(user, "id", userId);
        when(userReader.read(anyLong())).thenReturn(user);
        Long expect = 1L;

        // when
        Long actual = commentService.save(postId, commentWriteInfo);

        // then
        verify(postReader).readWithPessimisticWriteLock(postId);
        verify(userReader).read(userId);
        verify(commentAppender).append(post, user, commentWriteInfo.commentInfo());
        verify(eventPublisher).publish(NewCommentEvent.from(post));
        assertEquals(expect, actual);
    }

    @Test
    @DisplayName("게시글PK를 이용하여 댓글목록을 조회한다.")
    public void givenPostId_whenGetPostComments_thenShouldReturnCommentReadInfoList()
            throws Exception {
        // given
        Long postId = 1L;
        Post post = PostFixture.createPostWithId(postId);
        when(postReader.read(anyLong())).thenReturn(post);
        User user = post.getUser();
        List<Comment> comments = List.of(Comment.create(post, user, "content"));
        when(commentReader.read(any(Post.class))).thenReturn(comments);
        List<CommentReadInfo> expect = comments.stream().map(CommentReadInfo::from).toList();

        // when
        List<CommentReadInfo> actual = commentService.getPostComments(postId);

        // then
        verify(postReader).read(postId);
        verify(commentReader).read(post);
        assertEquals(expect.size(), actual.size());
    }

    @Test
    @DisplayName("댓글PK와 댓글작성정보를 이용해 댓글을 수정한다.")
    public void givenCommentIdCommentWriteInfo_whenUpdate_thenShouldReturnPostId()
            throws Exception {
        // given
        Long commentId = 1L;
        Long userId = 1L;
        CommentWriteInfo commentWriteInfo =
                CommentWriteInfo.create(userId, CommentInfo.of("content"));
        Comment comment = mock(Comment.class);
        when(commentReader.read(anyLong())).thenReturn(comment);
        Long postId = 1L;
        when(comment.getPostId()).thenReturn(postId);
        Long expect = 1L;

        // when
        Long actual = commentService.update(commentId, commentWriteInfo);

        // then
        verify(commentReader).read(commentId);
        verify(commentValidator).isOwnerId(commentWriteInfo.userId(), comment);
        verify(commentUpdater).update(comment, commentWriteInfo.commentInfo());
        assertEquals(expect, actual);
    }

    @Test
    @DisplayName("댓글PK를 이용해 댓글을 삭제한다.")
    public void givenCommentId_whenDelete_thenShouldReturnNothing() throws Exception {
        // given
        Long commentId = 1L;
        Comment comment = mock(Comment.class);
        when(commentReader.read(anyLong())).thenReturn(comment);
        Long postId = 1L;
        Post post = PostFixture.createPostWithId(postId);
        when(postReader.readWithPessimisticWriteLock(anyLong())).thenReturn(post);

        // when
        commentService.delete(commentId);

        // then
        verify(commentReader).read(commentId);
        verify(postReader).readWithPessimisticWriteLock(comment.getPostId());
        verify(commentDeleter).delete(comment);
    }
}
