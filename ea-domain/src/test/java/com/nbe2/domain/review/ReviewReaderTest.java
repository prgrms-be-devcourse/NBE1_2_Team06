package com.nbe2.domain.review;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ReviewReaderTest {

    @InjectMocks private ReviewReader reviewReader;

    @Mock ReviewRepository reviewRepository;

    @Test
    @DisplayName("응급실 Id로 리뷰 목록을 조회한다.")
    void
            givenEmergencyId_whenReviewRepositoryFindByEmergencyRoomId_thenShouldReturnReviewReadInfo() {}
}
