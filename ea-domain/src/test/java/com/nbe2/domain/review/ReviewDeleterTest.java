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

import com.nbe2.domain.emergencyroom.EmergencyRoomFixture;
import com.nbe2.domain.user.UserFixture;

@ExtendWith(MockitoExtension.class)
class ReviewDeleterTest {
    @Mock private ReviewRepository reviewRepository;

    @InjectMocks private ReviewDeleter reviewDeleter;

    @Test
    @DisplayName("리뷰ID로 리뷰를 삭제한다.")
    void givenReviewId_whenReviewDeleteById_ThenShouldDeleteReview() {
        // given
        Long reviewId = 1L;
        Review review =
                Review.from(
                        ReviewFixture.createReviewInfo(),
                        UserFixture.createUserWithId(),
                        EmergencyRoomFixture.createWithId());

        // when
        reviewDeleter.deleteReview(reviewId);

        // then
        verify(reviewRepository, times(1)).deleteById(reviewId);
    }
}
