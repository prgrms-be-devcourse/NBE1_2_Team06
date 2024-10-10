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

    // @TODO 기존의 fetch가 아니라 hpId로 캐싱해주는 로직이 필요함
    public RealTimeEmergencyRoomInfo reloadRealTimeEmergencyRooms(
            Coordinate currentLocation, String hospitalId) {
        fetch(currentLocation);
        RealTimeEmergencyRoomInfo realTimeEmergencyRoomInfo =
                realTimeEmergencyRoomInfoCacheManager.getInfo(hospitalId).get();
        return realTimeEmergencyRoomInfo;
    }
}
