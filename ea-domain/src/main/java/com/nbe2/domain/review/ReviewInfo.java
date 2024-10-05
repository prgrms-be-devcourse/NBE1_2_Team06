package com.nbe2.domain.review;

public record ReviewInfo(
        String content, Double speedScore, Double kindScore, Double facilityScore, String hpId) {}
