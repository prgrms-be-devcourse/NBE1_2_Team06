package com.nbe2.api.review;

import jakarta.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.review.dto.ReviewCreateRequest;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.review.ReviewInfo;
import com.nbe2.domain.review.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewApi {

    private final ReviewService reviewService;

    @PostMapping // insert
    public Response<Void> createReview(
            @Valid @RequestBody ReviewCreateRequest request,
            @AuthenticationPrincipal UserPrincipal userPrincipal
            // @RequestParam("userId") Long userId
            ) {
        ReviewInfo newRivewInfo = request.toReviewInfo();
        reviewService.writeReview(newRivewInfo, userPrincipal.userId());
        return Response.success();
    }
}
