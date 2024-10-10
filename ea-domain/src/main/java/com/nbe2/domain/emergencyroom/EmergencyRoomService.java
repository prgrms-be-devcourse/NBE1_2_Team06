package com.nbe2.domain.emergencyroom;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmergencyRoomService {

    private final CoordinateToRegionConverter coordinateConverter;
    private final RealTimeEmergencyRoomInfoFetcher realTimeInfoFetcher;
    private final DistanceCalculator distanceCalculator;
    private final EmergencyRoomInitializer emergencyRoomInitializer;
    private final EmergencyRoomReader emergencyRoomReader;
    private final EmergencyRoomDirections emergencyRoomDirections;
    private final RealTimeEmergencyRoomInfoCacheManager realTimeEmergencyRoomInfoCacheManager;

    @Transactional
    public void init() {
        emergencyRoomInitializer.init();
    }

    public List<RealTimeEmergencyRoomWithDistance> getRealTimeEmergencyRooms(
            Coordinate currentCoordinate) {
        Region region = coordinateConverter.convert(currentCoordinate);
        List<RealTimeEmergencyRoomInfo> realTimeInfos = realTimeInfoFetcher.fetch(region);
        return distanceCalculator.calculate(realTimeInfos, currentCoordinate);
    }

    public List<String> getEmergencyRoomListForName(String name) {
        return emergencyRoomReader.readByHospitalName(name);
    }

    public EmergencyRoomDirectionsInfo directionsEmergencyRoom(
            String myLocation, String hospitalName) {
        Coordinate byHospitalLocation = emergencyRoomReader.findByHospitalName(hospitalName);
        String latitudeAndLongitude = byHospitalLocation.convertorLatitudeAndLongitude();
        return emergencyRoomDirections.directionsEmergencyRoom(myLocation, latitudeAndLongitude);
    }

    public List<EmergencyRoomMapInfo> getEmergencyRooms(Coordinate coordinate, double distance) {
        return emergencyRoomReader.read(coordinate, distance);
    }

    public EmergencyRoomDetailInfo getEmergencyRoomDetail(
            String hospitalId, Coordinate coordinate) {
        EmergencyRoom emergencyRoom = emergencyRoomReader.read(hospitalId);

        RealTimeEmergencyRoomInfo realTimeCachingFlag =
                realTimeEmergencyRoomInfoCacheManager.getInfo(emergencyRoom.getHpId()).orElse(null);

        if (realTimeCachingFlag == null) { // real-time 정보가 캐싱이 안됐을 때
            realTimeInfoFetcher.reloadRealTimeEmergencyRooms(coordinate);
        }
        RealTimeEmergencyRoomInfo realTimeEmergencyRoomInfo =
                realTimeEmergencyRoomInfoCacheManager.getInfo(emergencyRoom.getHpId()).get();

        RealTimeEmergencyRoomWithDistance realTimeEmergencyRoomWithDistance =
                distanceCalculator.calculateDistance(coordinate, realTimeEmergencyRoomInfo);

        return EmergencyRoomDetailInfo.create(emergencyRoom, realTimeEmergencyRoomWithDistance);
    }
}
