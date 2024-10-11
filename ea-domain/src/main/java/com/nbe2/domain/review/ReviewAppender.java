package com.nbe2.domain.review;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomReader;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Component
@RequiredArgsConstructor
public class ReviewAppender {
    private final UserReader userReader;
    private final ReviewRepository reviewRepository;
    private final EmergencyRoomReader emergencyRoomReader;

    public Review append(Review review) {
        return reviewRepository.save(review);
    }

    public Review createReview(ReviewInfo reviewInfo, Long userId) {
        EmergencyRoom emergencyRoom = emergencyRoomReader.read(reviewInfo.hpId());
        User user = userReader.read(userId);

        return Review.from(reviewInfo, user, emergencyRoom);
    }
}
