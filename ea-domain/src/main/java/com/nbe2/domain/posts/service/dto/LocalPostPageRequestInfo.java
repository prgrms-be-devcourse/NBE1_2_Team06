package com.nbe2.domain.posts.service.dto;

import com.nbe2.common.dto.Page;
import com.nbe2.domain.posts.entity.City;

public record LocalPostPageRequestInfo(City city, Page page) {
    public static LocalPostPageRequestInfo create(final City city, final Page page) {
        return new LocalPostPageRequestInfo(city, page);
    }
}
