package com.nbe2.api.review.dto;

import com.nbe2.domain.review.ReviewReadInfo;

public record ReviewReadResponse(
        Long reviewId,
        Long emergencyRoomId,
        String email,
        String content,
        Double speedScore,
        Double kindScore,
        Double facilityScore) {
    public static ReviewReadResponse fromReviewReadInfo(ReviewReadInfo reviewReadInfo) {
        return new ReviewReadResponse(
                reviewReadInfo.reviewId(),
                reviewReadInfo.emergencyRoomId(),
                reviewReadInfo.content(),
                reviewReadInfo.email(),
                reviewReadInfo.speedScore(),
                reviewReadInfo.kindScore(),
                reviewReadInfo.facilityScore());
    }
}
