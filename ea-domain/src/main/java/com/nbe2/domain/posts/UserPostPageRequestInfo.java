package com.nbe2.domain.posts;

import com.nbe2.common.dto.Page;

public record UserPostPageRequestInfo(String email, Page page) {
    public static UserPostPageRequestInfo create(final String email, final Page page) {
        return new UserPostPageRequestInfo(email, page);
    }
}
