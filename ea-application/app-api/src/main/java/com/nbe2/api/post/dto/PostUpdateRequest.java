package com.nbe2.api.post.dto;

import com.nbe2.domain.posts.entity.City;
import com.nbe2.domain.posts.service.dto.PostUpdateCommand;

public record PostUpdateRequest(String title, String content, City city) {
    public PostUpdateCommand toCommand(final Long postsId, final PostUpdateRequest request) {
        return PostUpdateCommand.builder()
                .id(postsId)
                .title(request.title)
                .content(request.content)
                .city(request.city)
                .build();
    }
}
