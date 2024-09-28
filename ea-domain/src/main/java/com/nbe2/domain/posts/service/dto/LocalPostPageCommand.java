package com.nbe2.domain.posts.service.dto;

import lombok.Builder;

import com.nbe2.common.dto.Page;
import com.nbe2.domain.posts.entity.City;

public record LocalPostPageCommand(City city, Page page) {
    @Builder
    public LocalPostPageCommand(final City city, final Page page) {
        this.city = city;
        this.page = page;
    }
}
