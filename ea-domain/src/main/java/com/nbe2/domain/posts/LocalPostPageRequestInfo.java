package com.nbe2.domain.posts;

import com.nbe2.common.dto.Page;

public record LocalPostPageRequestInfo(City city, Page page) {
    public static LocalPostPageRequestInfo create(final City city, final Page page) {
        return new LocalPostPageRequestInfo(city, page);
    }
}
