package com.nbe2.api.post.dto;

import com.nbe2.domain.posts.entity.City;
import com.nbe2.domain.posts.service.dto.PostRegisterCommand;

public record PostRegisterRequest(String title, String content, City city) {
    public PostRegisterCommand toCommand(String email) {
        return PostRegisterCommand.builder()
                .email(email)
                .title(title)
                .content(content)
                .city(city)
                .build();
    }
}
