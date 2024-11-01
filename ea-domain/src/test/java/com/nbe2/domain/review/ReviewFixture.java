package com.nbe2.domain.review;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomFixture;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;

public class ReviewFixture {
    public static final String CONTENT = "좋아요";
    public static final Double SPEEDSCORE = 5.0;
    public static final Double KINDSCORE = 3.5;
    public static final Double FACILITYSCORE = 4.0;
    public static final String HPID = "HP001";

    public static Review createReview() {
        User user = UserFixture.createUserWithId();
        EmergencyRoom emergencyRoom = EmergencyRoomFixture.create();
        ReviewInfo reviewInfo = ReviewFixture.createReviewInfo();
        return Review.from(reviewInfo, user, emergencyRoom);
    }

    public static ReviewInfo createReviewInfo() {
        return new ReviewInfo(CONTENT, SPEEDSCORE, KINDSCORE, FACILITYSCORE, HPID);
    }

    public static ReviewUpdateInfo createReviewUpdateInfo(
            String updatedContent,
            Double updatedSpeedScore,
            Double updatedKindScore,
            Double updatedFacilityScore) {
        return new ReviewUpdateInfo(
                updatedContent, updatedSpeedScore, updatedKindScore, updatedFacilityScore);
    }

    public static ReviewReadInfo createReviewReadInfo(
            Review review, EmergencyRoom emergencyRoom, User user) {
        return new ReviewReadInfo(
                review.getReviewId(),
                emergencyRoom.getId(),
                user.getId(),
                user.getEmail(),
                CONTENT,
                SPEEDSCORE,
                KINDSCORE,
                FACILITYSCORE);
    }
}
