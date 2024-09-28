package com.nbe2.api.post.dto;

import com.nbe2.common.dto.Page;
import com.nbe2.domain.posts.entity.City;
import com.nbe2.domain.posts.service.dto.LocalPostPageCommand;

public record LocalPostPageRequest(City city, Integer page, Integer size) {
    public LocalPostPageCommand toCommand() {
        return LocalPostPageCommand.builder().city(city).page(new Page(page, size)).build();
    }
}
