package com.nbe2.domain.review;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewDeleter {
    private final ReviewRepository reviewRepository;

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
