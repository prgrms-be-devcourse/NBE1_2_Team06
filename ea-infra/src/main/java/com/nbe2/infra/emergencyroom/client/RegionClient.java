package com.nbe2.infra.emergencyroom.client;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.emergencyroom.Coordinate;
import com.nbe2.domain.emergencyroom.CoordinateToRegionConverter;
import com.nbe2.domain.emergencyroom.Region;
import com.nbe2.infra.emergencyroom.exception.RegionNotFoundException;
import com.nbe2.infra.kakao.client.KakaoClient;
import com.nbe2.infra.kakao.dto.KakaoRegionResponse;

@Component
@RequiredArgsConstructor
public class RegionClient implements CoordinateToRegionConverter {

    private final KakaoClient kakaoClient;

    @Override
    public Region convert(Coordinate coordinate) {
        return kakaoClient
                .getRegionData(coordinate.longitude(), coordinate.latitude())
                .documents()
                .stream()
                .filter(KakaoRegionResponse::isB)
                .map(KakaoRegionResponse::toRegion)
                .findFirst()
                .orElseThrow(() -> RegionNotFoundException.EXCEPTION);
    }
}
