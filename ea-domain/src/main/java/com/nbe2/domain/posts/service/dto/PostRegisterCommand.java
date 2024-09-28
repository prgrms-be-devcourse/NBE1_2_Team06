package com.nbe2.domain.posts.service.dto;

import lombok.Builder;

import com.nbe2.domain.posts.entity.City;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.user.User;

public record PostRegisterCommand(String email, String title, String content, City city) {
    @Builder
    public PostRegisterCommand(String email, String title, String content, City city) {
        this.email = email;
        this.title = title;
        this.content = content;
        this.city = city;
    }

    public Post toEntity(User user) {
        return Post.builder().user(user).title(title).content(content).city(city).build();
    }
}
