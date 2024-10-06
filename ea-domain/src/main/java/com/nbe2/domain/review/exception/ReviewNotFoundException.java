package com.nbe2.domain.review.exception;

import com.nbe2.common.exception.DomainException;

public class ReviewNotFoundException extends DomainException {
    public static final DomainException EXCEPTION = new ReviewNotFoundException();

    private ReviewNotFoundException() {
        super(ReviewErrorCode.REVIEW_NOT_FOUND);
    }
}
