package com.nbe2.domain.emergencyroom;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RealTimeEmergencyRoomInfoFetcher {

    private final EmergencyRoomClient realTimeClient;
    private final RealTimeEmergencyRoomInfoCacheManager cacheManager;

    public List<RealTimeEmergencyRoomInfo> fetch(Region region) {
        List<RealTimeEmergencyRoomInfo> realTimeInfo = realTimeClient.getRealTimeInfo(region);
        cacheManager.cache(realTimeInfo);
        return realTimeInfo;
    }
}
