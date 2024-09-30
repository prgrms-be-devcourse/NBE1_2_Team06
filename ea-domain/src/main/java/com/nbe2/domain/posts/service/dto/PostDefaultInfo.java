package com.nbe2.domain.posts.service.dto;

import com.nbe2.domain.posts.entity.City;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.user.User;

// create, update 에 사용되는 정보
public record PostDefaultInfo(String title, String content, City city) {
    public static PostDefaultInfo create(
            final String title, final String content, final City city) {
        return new PostDefaultInfo(title, content, city);
    }

    public Post toEntity(final User user) {
        return Post.builder().user(user).title(title).content(content).city(city).build();
    }
}
