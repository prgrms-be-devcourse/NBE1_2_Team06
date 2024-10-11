package com.nbe2.api.review;

import jakarta.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.review.dto.ReviewCreateRequest;
import com.nbe2.api.review.dto.ReviewReadResponse;
import com.nbe2.api.review.dto.ReviewUpdateRequest;
import com.nbe2.common.annotation.PageDefault;
import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.review.ReviewInfo;
import com.nbe2.domain.review.ReviewReadInfo;
import com.nbe2.domain.review.ReviewService;
import com.nbe2.domain.review.ReviewUpdateInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewApi {

    private final ReviewService reviewService;

    @PostMapping // insert
    public Response<Void> createReview(
            @Valid @RequestBody ReviewCreateRequest request,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        ReviewInfo newRivewInfo = request.toReviewInfo();
        reviewService.writeReview(newRivewInfo, userPrincipal.userId());
        return Response.success();
    }

    @GetMapping
    public Response<PageResult<ReviewReadResponse>> getReview( // read
            @RequestParam("emergencyRoomId") Long emergencyRoomId, @PageDefault Page page) {
        PageResult<ReviewReadInfo> reviewReadInfoPageResult =
                reviewService.readReview(page, emergencyRoomId);
        return Response.success(
                reviewReadInfoPageResult.map(ReviewReadResponse::fromReviewReadInfo));
    }

    @GetMapping("/search") // search email
    public Response<PageResult<ReviewReadResponse>> searchReview(
            @RequestParam("email") String email, @PageDefault Page page) {
        PageResult<ReviewReadInfo> reviewReadPageResult = reviewService.searchByEmail(email, page);
        return Response.success(reviewReadPageResult.map(ReviewReadResponse::fromReviewReadInfo));
    }

    @PutMapping("/{reviewId}") // update
    public Response<Void> updateReview(
            @PathVariable Long reviewId,
            @Valid @RequestBody ReviewUpdateRequest request,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        ReviewUpdateInfo updateInfo = request.toReviewUpdateInfo();
        reviewService.updateReview(updateInfo, reviewId, userPrincipal.userId());
        return Response.success();
    }

    @DeleteMapping("/{reviewId}") // delete
    public Response<Void> deleteReview(
            @PathVariable Long reviewId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        reviewService.deleteReview(reviewId, userPrincipal.userId());
        return Response.success();
    }
}
