package com.nbe2.api.emergencyroom.dto;

import com.nbe2.domain.emergencyroom.EmergencyRoomMapInfo;

public record EmergencyRoomMapResponse(
        Long emergencyRoomId,
        String hpId,
        String hospitalName,
        String address,
        String simpleMap,
        double longitude,
        double latitude,
        double distance) {

    public static EmergencyRoomMapResponse from(EmergencyRoomMapInfo mapInfo) {
        return new EmergencyRoomMapResponse(
                mapInfo.emergencyRoomId(),
                mapInfo.hpId(),
                mapInfo.hospitalName(),
                mapInfo.address(),
                mapInfo.simpleMap(),
                mapInfo.coordinate().getLongitude(),
                mapInfo.coordinate().getLatitude(),
                convertToKilometers(mapInfo.distance()));
    }

    private static double convertToKilometers(double distanceInMeters) {
        return Math.round((distanceInMeters / 1000) * 100) / 100.0;
    }
}
