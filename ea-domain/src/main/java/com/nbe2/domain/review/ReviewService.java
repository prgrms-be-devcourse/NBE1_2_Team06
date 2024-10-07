package com.nbe2.domain.review;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.global.util.PagingUtil;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewAppender reviewAppender;
    private final ReviewReader reviewReader;
    private final ReviewDeleter reviewDeleter;
    private final ReviewUpdater reviewUpdater;

    public void writeReview(ReviewInfo reviewInfo, Long userId) {
        Review newReview = reviewAppender.createReview(reviewInfo, userId);
        reviewAppender.append(newReview);
    }

    @Transactional(readOnly = true)
    public PageResult<ReviewReadInfo> readReview(Page page, Long emergencyRoomId) {
        return reviewReader.readAll(PagingUtil.toPageRequest(page), emergencyRoomId);
    }

    public void deleteReview(Long reviewId) {
        reviewDeleter.deleteReview(reviewId);
    }

    public void updateReview(ReviewUpdateInfo updateInfo, Long reviewId) {
        reviewUpdater.updateReview(updateInfo, reviewId);
    }

    public PageResult<ReviewReadInfo> searchByEmail(Long userId, Page page) {
        return reviewReader.searchUserId(PagingUtil.toPageRequest(page), userId);
    }
}