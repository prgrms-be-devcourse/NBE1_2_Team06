package com.nbe2.domain.emergencyroom;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RealTimeEmergencyRoomInfoFetcher {

    private final EmergencyRoomClient realTimeClient;
    private final RealTimeEmergencyRoomInfoCacheManager cacheManager;
    private final RealTimeEmergencyRoomInfoCacheManager realTimeEmergencyRoomInfoCacheManager;
    private final CoordinateToRegionConverter coordinateToRegionConverter;

    public List<RealTimeEmergencyRoomInfo> fetch(Region region) {
        List<RealTimeEmergencyRoomInfo> realTimeInfo = realTimeClient.getRealTimeInfo(region);
        cacheManager.cache(realTimeInfo);
        return realTimeInfo;
    }

    public void reloadRealTimeEmergencyRooms(Coordinate currentLocation) {
        Region region = coordinateToRegionConverter.convert(currentLocation);
        fetch(region);
    }
}
