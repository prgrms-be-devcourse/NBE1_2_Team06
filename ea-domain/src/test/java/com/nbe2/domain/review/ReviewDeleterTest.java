package com.nbe2.domain.review;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReviewDeleterTest {
    @Mock private ReviewRepository reviewRepository;

    @InjectMocks private ReviewDeleter reviewDeleter;

    @Test
    @DisplayName("리뷰ID로 리뷰를 삭제한다.")
    void givenReviewId_whenReviewDeleteById_ThenShouldDeleteReview() {
        // given
        Long reviewId = 1L;

        // when
        reviewDeleter.deleteReview(reviewId);

        // then
        verify(reviewRepository, times(1)).deleteById(reviewId);
    }
}
