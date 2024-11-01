package com.nbe2.domain.emergencyroom;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RealTimeEmergencyRoomInfoCacheManager {

    private final RealTimeEmergencyRoomInfoCacheRepository cacheRepository;

    public void cache(List<RealTimeEmergencyRoomInfo> info) {
        info.forEach(cacheRepository::cache);
    }

    public void cache(RealTimeEmergencyRoomInfo info) {
        cacheRepository.cache(info);
    }

    public Optional<RealTimeEmergencyRoomInfo> getInfo(String hpId) {
        return cacheRepository.findById(hpId);
    }
}
