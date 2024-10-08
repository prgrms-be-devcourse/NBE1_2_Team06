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
        List<RealTimeEmergencyRoomInfo> realTimeInfos =
                realTimeInfoFetcher.fetch(currentCoordinate);
        return distanceCalculator.calculate(realTimeInfos, currentCoordinate);
    }

    public List<String> getEmergencyRoomListForName(String name) {
        return emergencyRoomReader.readByHospitalName(name);
    }

    public EmergencyRoomDirectionsInfo directionsEmergencyRoom(
            String myLocation, String hospitalName) {
        Coordinate byHospitalLocation = emergencyRoomReader.readCoordinate(hospitalName);
        String latitudeAndLongitude = byHospitalLocation.convertorLatitudeAndLongitude();
        return emergencyRoomDirections.directionsEmergencyRoom(myLocation, latitudeAndLongitude);
    }

    public List<EmergencyRoomMapInfo> getEmergencyRooms(Coordinate coordinate, double distance) {
        return emergencyRoomReader.read(coordinate, distance);
    }

    public EmergencyRoomDetailInfo getEmergencyRoomDetail(
            String hospitalId, Coordinate coordinate) {
        EmergencyRoom emergencyRoom = emergencyRoomReader.read(hospitalId);

        RealTimeEmergencyRoomInfo realTimeEmergencyRoomInfo =
                realTimeEmergencyRoomInfoCacheManager
                        .getInfo(emergencyRoom.getHpId())
                        .orElseGet(
                                () ->
                                        realTimeInfoFetcher.reloadRealTimeEmergencyRooms(
                                                coordinate, hospitalId));

        RealTimeEmergencyRoomWithDistance realTimeEmergencyRoomWithDistance =
                distanceCalculator.calculateDistance(coordinate, realTimeEmergencyRoomInfo);

        return EmergencyRoomDetailInfo.create(emergencyRoom, realTimeEmergencyRoomWithDistance);
    }
}
