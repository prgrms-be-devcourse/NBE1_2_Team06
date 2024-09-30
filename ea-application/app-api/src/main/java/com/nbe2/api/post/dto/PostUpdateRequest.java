package com.nbe2.api.post.dto;

import com.nbe2.domain.posts.entity.City;

public record PostUpdateRequest(String title, String content, City city) {}
