package com.nbe2.domain.review;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;

@ExtendWith(MockitoExtension.class)
class ReviewValidatorTest {
    @InjectMocks private ReviewValidator reviewValidator;

    @Mock ReviewReader reviewReader;

    private Review review;
    private final Long reviewId = 1L;
    private final Long validUserId = 1L;
    private final Long invalidUserId = 2L;

    @BeforeEach
    void setUp() {
        review = ReviewFixture.createReview();
    }

    @Test
    @DisplayName("유효한 사용자ID로 검증에 성공한다.")
    void givenReviewIdUserId_whenReadReview_thenShouldReturnTrue() {
        // given
        User user = UserFixture.createUserWithId();

        // when
        when(reviewReader.readReview(reviewId)).thenReturn(review);
        boolean isValid = reviewValidator.isValidUserId(reviewId, validUserId);

        // then
        assertTrue(isValid);
        verify(reviewReader, times(1)).readReview(reviewId);
    }

    @Test
    @DisplayName("유효하지 않은 사용자ID로 검증에 실패한다.")
    void givenReviewId_whenReadReview_thenShouldReturnFalse() {
        // Given: reviewReader가 리뷰를 반환하도록 설정
        User user = UserFixture.createUserWithId();

        // When: 잘못된 사용자 ID로 검증
        when(reviewReader.readReview(reviewId)).thenReturn(review);
        boolean isValid = reviewValidator.isValidUserId(reviewId, invalidUserId);

        // Then: 검증 실패
        assertFalse(isValid);
        verify(reviewReader, times(1)).readReview(reviewId);
    }
}
