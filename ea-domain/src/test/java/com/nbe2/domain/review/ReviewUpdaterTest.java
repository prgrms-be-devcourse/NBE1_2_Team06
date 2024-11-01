package com.nbe2.domain.review;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReviewUpdaterTest {
    @InjectMocks private ReviewUpdater reviewUpdater;

    @Mock ReviewReader reviewReader;

    @Mock ReviewRepository reviewRepository;

    @Test
    @DisplayName("유효한 리뷰ID와 유효한 내용으로 리뷰를 수정한다.")
    void givenReviewUpdateInfoAndReviewId_whenReviewReaderReadReview_thenShouldUpdateReview() {
        // Given
        Review review = ReviewFixture.createReview();
        Long reviewId = 1L;

        ReviewUpdateInfo updateInfo = ReviewFixture.createReviewUpdateInfo("수정된 리뷰", 4.5, 3.0, 4.0);

        // Mock 설정: 리뷰 ID로 리뷰 읽기 동작
        when(reviewReader.readReview(reviewId)).thenReturn(review);

        // When
        reviewUpdater.updateReview(updateInfo, reviewId);

        // Then
        // 리뷰가 업데이트되었는지 확인
        assertThat(review.getContent()).isEqualTo("수정된 리뷰");
        assertThat(review.getSpeedScore()).isEqualTo(4.5);
        assertThat(review.getKindScore()).isEqualTo(3.0);
        assertThat(review.getFacilityScore()).isEqualTo(4.0);

        // 리뷰 저장 메서드 호출 확인
        verify(reviewRepository, times(1)).save(review);
        // 리뷰 읽기 메서드 호출 확인
        verify(reviewReader, times(1)).readReview(reviewId);
    }
}
