package com.nbe2.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.review.exception.ReviewNotFoundException;
import com.nbe2.domain.user.UserReader;

@Component
@RequiredArgsConstructor
public class ReviewReader {
    private final ReviewRepository reviewRepository;
    private final Validator validator;
    private final UserReader userReader;

    public PageResult<ReviewReadInfo> readAll(Pageable pageable, Long emergencyRoomId) {
        Page<ReviewReadInfo> readReviews =
                reviewRepository
                        .findByEmergencyRoomId(emergencyRoomId, pageable)
                        .map(ReviewReadInfo::convertToReviewInfo);
        return new PageResult<>(
                readReviews.getContent(), readReviews.getTotalPages(), readReviews.hasNext());
    }

    public Review readReview(Long reviewId) {
        return reviewRepository
                .findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);
    }

    public PageResult<ReviewReadInfo> searchUserId(PageRequest pageRequest, Long userId) {
        Page<ReviewReadInfo> readReviews =
                reviewRepository
                        .findByUserId(userId, pageRequest)
                        .map(ReviewReadInfo::convertToReviewInfo);
        return new PageResult<>(
                readReviews.getContent(), readReviews.getTotalPages(), readReviews.hasNext());
    }
}
