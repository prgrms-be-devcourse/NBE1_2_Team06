package com.nbe2.domain.review;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomFixture;
import com.nbe2.domain.emergencyroom.EmergencyRoomReader;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;
import com.nbe2.domain.user.UserReader;

@ExtendWith(MockitoExtension.class)
class ReviewAppenderTest {
    @InjectMocks private ReviewAppender reviewAppender;

    @Mock private UserReader userReader;

    @Mock private ReviewRepository reviewRepository;

    @Mock EmergencyRoomReader emergencyRoomReader;

    @Test
    @DisplayName("리뷰를 저장한다.")
    void givenReview_whenReviewRepositroySave_thenShouldSaveReview() {
        // given
        EmergencyRoom emergencyRoom = EmergencyRoomFixture.create();
        User user = UserFixture.createUserWithId();
        Review review = Review.from(ReviewFixture.createReviewInfo(), user, emergencyRoom);

        // when
        when(reviewAppender.append(review)).thenReturn(review);

        // then
        assertEquals(review, reviewAppender.append(review));
    }
}
