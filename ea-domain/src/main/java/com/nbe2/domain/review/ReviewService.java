package com.nbe2.domain.review;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewAppender reviewAppender;

    public void writeReview(ReviewInfo reviewInfo, Long userId) {
        Review newReview = reviewAppender.createReview(reviewInfo, userId);
        reviewAppender.append(newReview);
    }
}
