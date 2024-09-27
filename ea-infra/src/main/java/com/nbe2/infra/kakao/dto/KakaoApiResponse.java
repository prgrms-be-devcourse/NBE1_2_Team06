package com.nbe2.infra.kakao.dto;

import java.util.List;

public record KakaoApiResponse<T>(List<T> documents) {}
