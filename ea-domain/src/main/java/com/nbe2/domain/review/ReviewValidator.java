package com.nbe2.domain.review;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewValidator {
    private final ReviewReader reviewReader;

    public boolean isValid(Long reviewId, Long userId) {
        Review review = reviewReader.readReview(reviewId);

        if (review.getUser().getId().equals(userId)) {
            return true;
        }
        return false;
    }
}
