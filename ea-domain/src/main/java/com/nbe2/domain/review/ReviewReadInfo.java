package com.nbe2.domain.review;

public record ReviewReadInfo(
        Long reviewId,
        Long emergencyRoomId,
        Long userId,
        String email,
        String content,
        Double speedScore,
        Double kindScore,
        Double facilityScore) {
    public static ReviewReadInfo convertToReviewInfo(Review review) {
        return new ReviewReadInfo(
                review.getReviewId(),
                review.getEmergencyRoom().getId(),
                review.getUser().getId(),
                review.getUser().getEmail(),
                review.getContent(),
                review.getSpeedScore(),
                review.getKindScore(),
                review.getFacilityScore());
    }
}
