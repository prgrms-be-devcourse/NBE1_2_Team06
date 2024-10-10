package com.nbe2.domain.review;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewUpdater {
    private final ReviewRepository reviewRepository;
    private final ReviewReader reviewReader;

    public void updateReview(ReviewUpdateInfo updateInfo, Long reviewId) {
        Review before = reviewReader.readReview(reviewId);
        before.updateReview(updateInfo);
        reviewRepository.save(before);
    }
}
