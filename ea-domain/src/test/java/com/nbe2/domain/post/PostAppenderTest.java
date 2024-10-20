package com.nbe2.domain.post;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.posts.*;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;

@ExtendWith(MockitoExtension.class)
public class PostAppenderTest {

    @InjectMocks PostAppender postAppender;

    @Mock PostFileRegisterer postFileRegisterer;

    @Mock PostRepository postRepository;

    @Test
    @DisplayName("회원과 게시글등록정보를 이용하여 게시글을 저장한다.")
    public void givenUserPostWriteInfo_whenAppend_thenShouldReturnPostId() throws Exception {
        // given
        User user = UserFixture.createUser();
        PostWriteInfo postWriteInfo =
                PostWriteInfo.create("title", "content", City.ANDONG, Optional.empty());
        Post expected = PostFixture.createPostWithId(1L);

        when(postRepository.save(any(Post.class))).thenReturn(expected);

        // when
        Long actual = postAppender.append(user, postWriteInfo);

        // then
        verify(postFileRegisterer).register(expected, postWriteInfo.fileIdList());
        verify(postRepository).save(any(Post.class));
    }
}
