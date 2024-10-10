package com.nbe2.domain.review;

public record ReviewUpdateInfo(
        String content, Double speedScore, Double kindScore, Double facilityScore) {}
