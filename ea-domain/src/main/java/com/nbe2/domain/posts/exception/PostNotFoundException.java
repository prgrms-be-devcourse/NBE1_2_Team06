package com.nbe2.domain.posts.exception;

import com.nbe2.common.exception.DomainException;

public class PostNotFoundException extends DomainException {

    public static final DomainException EXCEPTION = new PostNotFoundException();

    private PostNotFoundException() {
        super(PostErrorCode.POST_NOT_FOUND);
    }
}
