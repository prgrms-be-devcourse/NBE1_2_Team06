package com.nbe2.infra.kakao.dto;

import com.nbe2.domain.emergencyroom.Region;

public record KakaoRegionResponse(
        String region_type,
        String address_name,
        String region_1depth_name,
        String region_2depth_name,
        String region_3depth_name,
        String region_4depth_name,
        String code,
        Double x,
        Double y) {

    // 법정동
    public boolean isB() {
        return region_type.equals("B");
    }

    public Region toRegion() {
        return new Region(region_1depth_name, region_2depth_name);
    }
}
