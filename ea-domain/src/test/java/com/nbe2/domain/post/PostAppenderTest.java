package com.nbe2.domain.post;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.nbe2.domain.posts.PostAppender;

@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
public class PostAppenderTest {

    @InjectMocks PostAppender postAppender;

    @Test
    @DisplayName("게시글을 저장한다.")
    public void 게시글등록() throws Exception {
        // given
        // when

        // then
    }
}
