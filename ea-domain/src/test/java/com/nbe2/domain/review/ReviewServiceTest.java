package com.nbe2.domain.review;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.user.UserReader;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {
    @InjectMocks private ReviewService reviewService;

    @Mock private ReviewAppender reviewAppender;

    @Mock private ReviewReader reviewReader;

    @Mock private ReviewDeleter reviewDeleter;

    @Mock private ReviewUpdater reviewUpdater;

    @Mock private ReviewValidator reviewValidator;

    @Mock private UserReader userReader;
}
