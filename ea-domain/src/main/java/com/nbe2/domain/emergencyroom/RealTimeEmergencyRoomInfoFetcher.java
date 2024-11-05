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
    private final CoordinateToRegionConverter coordinateConverter;

    public List<RealTimeEmergencyRoomInfo> fetch(Coordinate currentCoordinate) {
        Region region = coordinateConverter.convert(currentCoordinate);
        List<RealTimeEmergencyRoomInfo> realTimeInfo = realTimeClient.getRealTimeInfo(region);
        cacheManager.cache(realTimeInfo);
        return realTimeInfo;
    }

    public RealTimeEmergencyRoomInfo reloadRealTimeEmergencyRooms(
            Coordinate currentLocation, String hospitalId) {
        fetch(currentLocation);
        RealTimeEmergencyRoomInfo realTimeEmergencyRoomInfo =
                realTimeEmergencyRoomInfoCacheManager.getInfo(hospitalId).get();
        return realTimeEmergencyRoomInfo;
    }
}
