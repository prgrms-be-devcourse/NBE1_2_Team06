package com.nbe2.domain.review;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.review.exception.ReviewNotFoundException;

@ExtendWith(MockitoExtension.class)
class ReviewReaderTest {

    @InjectMocks private ReviewReader reviewReader;

    @Mock ReviewRepository reviewRepository;

    @Test
    @DisplayName("응급실 Id로 리뷰 목록 페이지를 조회한다.")
    void
            givenPageableEmergencyRoomId_whenReviewRepositoryFindByEmergencyRoomId_thenShouldReturnReviewPage() {
        // given
        Long emergencyRoomId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        List<Review> reviews = List.of(ReviewFixture.createReview(), ReviewFixture.createReview());
        Page<Review> reviewPage = new PageImpl<>(reviews);
        when(reviewRepository.findByEmergencyRoomId(emergencyRoomId, pageable))
                .thenReturn(reviewPage);

        // when
        PageResult<ReviewReadInfo> pageResult = reviewReader.readAll(pageable, emergencyRoomId);

        // then
        assertEquals(2, pageResult.content().size()); // 2개의 리뷰가 포함된 페이지 확인
        assertEquals(1, pageResult.totalPage()); // 총 1 페이지
        assertFalse(pageResult.hasNext()); // 다음 페이지가 없는지 확인

        verify(reviewRepository, times(1)).findByEmergencyRoomId(emergencyRoomId, pageable);
    }

    @Test
    @DisplayName("리뷰ID로 리뷰를 조회한다.")
    void givenReviewId_WhenReviewRepositoryFindById_thenShouldReturnReview() {
        // given
        Long reviewId = 1L;
        Review expectedReview = ReviewFixture.createReview();

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(expectedReview));

        // when
        Review actualReview = reviewReader.readReview(reviewId);

        // then
        assertEquals(expectedReview, actualReview);
        verify(reviewRepository, times(1)).findById(reviewId);
    }

    @Test
    @DisplayName("리뷰ID로 조회 시 결과가 없다면 예외를 던진다.")
    void givenReviewId_WhenReviewRepositoryFindById_thenReviewNotFoundShouldThrowsException() {
        // given
        Long reviewId = 1L;
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        // when then
        assertThrows(ReviewNotFoundException.class, () -> reviewReader.readReview(reviewId));
    }

    @Test
    @DisplayName("유저 ID로 리뷰 목록 페이지를 조회한다.")
    void givenPageableUserId_whenReviewRepositoryFindByUserId_thenShouldReturnReviewPage() {
        // given
        Long userId = 1L;
        PageRequest pageRequest = PageRequest.of(0, 10);
        Pageable pageable = PageRequest.of(0, 10);
        List<Review> reviews = List.of(ReviewFixture.createReview(), ReviewFixture.createReview());
        Page<Review> reviewPage = new PageImpl<>(reviews);
        when(reviewRepository.findByUserId(userId, pageable)).thenReturn(reviewPage);

        // when
        PageResult<ReviewReadInfo> pageResult = reviewReader.searchUserId(pageRequest, userId);

        // then
        assertEquals(2, pageResult.content().size()); // 2개의 리뷰가 포함된 페이지 확인
        assertEquals(1, pageResult.totalPage()); // 총 1 페이지
        assertFalse(pageResult.hasNext()); // 다음 페이지가 없는지 확인

        verify(reviewRepository, times(1)).findByUserId(userId, pageable);
    }
}
