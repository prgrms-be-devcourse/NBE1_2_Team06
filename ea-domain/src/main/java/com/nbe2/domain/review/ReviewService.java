package com.nbe2.domain.review;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.global.util.PagingUtil;
import com.nbe2.domain.review.exception.ReviewNoAccessAuthority;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewAppender reviewAppender;
    private final ReviewReader reviewReader;
    private final ReviewDeleter reviewDeleter;
    private final ReviewUpdater reviewUpdater;
    private final UserReader userReader;
    private final ReviewValidator reviewValidator;

    public void writeReview(ReviewInfo reviewInfo, Long userId) {
        Review newReview = reviewAppender.createReview(reviewInfo, userId);
        reviewAppender.append(newReview);
    }

    @Transactional(readOnly = true)
    public PageResult<ReviewReadInfo> readReview(Page page, Long emergencyRoomId) {
        return reviewReader.readAll(PagingUtil.toPageRequest(page), emergencyRoomId);
    }

    public void deleteReview(Long reviewId, Long userId) {
        if (!reviewValidator.isValidUserId(reviewId, userId)) {
            throw ReviewNoAccessAuthority.EXCEPTION;
        }
        reviewDeleter.deleteReview(reviewId);
    }

    public void updateReview(ReviewUpdateInfo updateInfo, Long reviewId, Long userId) {
        if (!reviewValidator.isValidUserId(reviewId, userId)) {
            throw ReviewNoAccessAuthority.EXCEPTION;
        }
        reviewUpdater.updateReview(updateInfo, reviewId);
    }

    @Transactional(readOnly = true)
    public PageResult<ReviewReadInfo> searchByEmail(String email, Page page) {
        User user = userReader.read(email);
        return reviewReader.searchUserId(PagingUtil.toPageRequest(page), user.getId());
    }
}
