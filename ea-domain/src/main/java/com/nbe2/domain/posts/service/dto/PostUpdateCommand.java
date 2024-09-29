package com.nbe2.domain.posts.service.dto;

import lombok.Builder;

import com.nbe2.domain.posts.entity.City;

public record PostUpdateCommand(Long id, String title, String content, City city) {

    @Builder
    public PostUpdateCommand(
            final Long id, final String title, final String content, final City city) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.city = city;
    }
}
