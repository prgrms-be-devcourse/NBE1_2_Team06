package com.nbe2.domain.review.exception;

import com.nbe2.common.exception.DomainException;

public class ReviewNoAccessAuthority extends DomainException {
    public static final DomainException EXCEPTION = new ReviewNoAccessAuthority();

    private ReviewNoAccessAuthority() {
        super(ReviewErrorCode.REVIEW_NO_ACCESS_AUTHORITY);
    }
}
