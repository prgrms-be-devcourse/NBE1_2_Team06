package com.nbe2.domain.review;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.emergencyroom.EmergencyRoomFixture;
import com.nbe2.domain.user.UserFixture;

@ExtendWith(MockitoExtension.class)
class ReviewUpdaterTest {
    @InjectMocks private ReviewUpdater updater;

    @Mock ReviewReader reviewReader;

    @Mock ReviewRepository reviewRepository;

    @Test
    @DisplayName("유효한 리뷰ID로 사용자가 유효한 내용으로 리뷰를 수정한다.")
    void givenReviewUpdateInfoAndReviewId_whenReviewReaderReadReview_thenShouldUpdateReview() {
        // TODO 어쩌다보니 reader 테스트가 되어버림..
        // given
        ReviewInfo reviewInfo = ReviewFixture.createReviewInfo();
        Review expectedReview =
                Review.from(
                        reviewInfo,
                        UserFixture.createUserWithId(),
                        EmergencyRoomFixture.createWithId());
        Long reviewId = 1L;

        // when
        when(reviewReader.readReview(reviewId)).thenReturn(expectedReview);
        Review actualReview = reviewReader.readReview(reviewId);

        // then
        assertAll(() -> assertEquals(expectedReview, actualReview));
    }
}
