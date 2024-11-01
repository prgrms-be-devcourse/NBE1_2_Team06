package com.nbe2.domain.review;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomFixture;
import com.nbe2.domain.review.exception.ReviewNoAccessAuthority;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;
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

    private ReviewInfo expectedReviewInfo;
    private Review expectedReview;
    private User user;
    private EmergencyRoom emergencyRoom;
    private ReviewReadInfo expectedReviewReadInfo;

    @BeforeEach
    void setUp() {
        emergencyRoom = EmergencyRoomFixture.createWithId();
        expectedReviewInfo = ReviewFixture.createReviewInfo();
        user = UserFixture.createUserWithId();
        expectedReview = ReviewFixture.createReview();
    }

    @Test
    @DisplayName("사용자가 리뷰를 작성한다.")
    void givenReviewInfoUserId_whenReviewAppenderCreateReview_thenAppendShouldSave() {
        // given
        Long userId = user.getId();
        Long emergencyRoomId = emergencyRoom.getId();
        when(reviewAppender.createReview(expectedReviewInfo, userId)).thenReturn(expectedReview);

        // when
        reviewService.writeReview(expectedReviewInfo, userId);

        // then
        verify(reviewAppender).createReview(expectedReviewInfo, userId);
    }

    @Test
    @DisplayName("응급실ID로 리뷰를 조회한다.")
    void givenEmergencyRoomId_whenReviewReaderReadAll_thenShouldReturnPageResultReviewReadInfo() {
        // given
        Long emergencyRoomId = emergencyRoom.getId();
        Long userId = user.getId();
        expectedReviewReadInfo =
                ReviewFixture.createReviewReadInfo(expectedReview, emergencyRoom, user);
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<ReviewReadInfo> reviews = List.of(expectedReviewReadInfo);
        PageResult<ReviewReadInfo> expectedPageResult = new PageResult<>(reviews, 1, false);

        when(reviewReader.readAll(pageRequest, emergencyRoomId)).thenReturn(expectedPageResult);

        // when
        PageResult<ReviewReadInfo> actualPageResult =
                reviewService.readReview(new Page(0, 10), emergencyRoomId);

        // then
        assertEquals(expectedPageResult, actualPageResult);
        verify(reviewReader, times(1)).readAll(pageRequest, emergencyRoomId);
    }

    @Test
    @DisplayName("유효한 사용자가 리뷰를 삭제한다.")
    void givenReviewIdUserId_whenReviewDeleterDeleteReview_thenShouldDeleteReview() {
        // given
        Long reviewId = expectedReview.getReviewId();
        Long userId = user.getId();
        when(reviewValidator.isValidUserId(reviewId, userId)).thenReturn(true);

        // when
        reviewService.deleteReview(reviewId, userId);

        // then
        verify(reviewDeleter, times(1)).deleteReview(reviewId);
    }

    @Test
    @DisplayName("권한 없는 유저가 리뷰 삭제 또는 수정 시 예외를 던진다.")
    void givenReviewIdUserId_whenReviewDeleterDeleteReview_thenThrowException() {
        // given
        Long reviewId = expectedReview.getReviewId();
        Long userId = user.getId();

        // when
        when(reviewValidator.isValidUserId(reviewId, userId)).thenReturn(false);

        // then
        assertThrows(
                ReviewNoAccessAuthority.class, () -> reviewService.deleteReview(reviewId, userId));
    }

    @Test
    @DisplayName("유효한 사용자가 리뷰를 수정한다.")
    void givenReviewUpdateInfo_whenReviewUpdaterUpdateReview_thenShouldUpdateReview() {
        // given
        Long reviewId = expectedReview.getReviewId();
        Long userId = user.getId();
        ReviewUpdateInfo updateInfo =
                ReviewFixture.createReviewUpdateInfo("Updated Content", 4.0, 4.0, 4.0);
        when(reviewValidator.isValidUserId(reviewId, userId)).thenReturn(true);

        // when
        reviewService.updateReview(updateInfo, reviewId, userId);

        // then
        verify(reviewUpdater, times(1)).updateReview(updateInfo, reviewId);
    }

    @Test
    @DisplayName("유저 이메일로 리뷰를 검색한다.")
    void givenEmail_whenReviewReaderSearchUserId_thenReturnPageResultReviewReadInfo() {
        // given
        String email = user.getEmail();
        Long userId = user.getId();
        expectedReviewReadInfo =
                ReviewFixture.createReviewReadInfo(expectedReview, emergencyRoom, user);
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<ReviewReadInfo> reviews = List.of(expectedReviewReadInfo);
        PageResult<ReviewReadInfo> expectedPageResult = new PageResult<>(reviews, 1, false);
        when(userReader.read(email)).thenReturn(user);
        when(reviewReader.searchUserId(pageRequest, userId)).thenReturn(expectedPageResult);

        // when
        PageResult<ReviewReadInfo> actualPageResult =
                reviewService.searchByEmail(email, new Page(0, 10));

        // then
        assertEquals(expectedPageResult, actualPageResult);
        verify(reviewReader, times(1)).searchUserId(pageRequest, userId);
    }
}
