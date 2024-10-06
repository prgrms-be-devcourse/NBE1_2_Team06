package com.nbe2.domain.review;

public record ReviewReadInfo(
        Long emergencyRoomId,
        Long userId,
        String content,
        Double speedScore,
        Double kindScore,
        Double facilityScore) {
    public static ReviewReadInfo convertToReviewInfo(Review review) {
        return new ReviewReadInfo(
                review.getEmergencyRoom().getId(),
                review.getUser().getId(),
                review.getContent(),
                review.getSpeedScore(),
                review.getKindScore(),
                review.getFacilityScore());
    }
}
