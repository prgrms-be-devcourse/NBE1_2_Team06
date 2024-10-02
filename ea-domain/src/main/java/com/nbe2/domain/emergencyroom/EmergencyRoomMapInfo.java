package com.nbe2.domain.emergencyroom;

import org.locationtech.jts.geom.Point;

import com.querydsl.core.annotations.QueryProjection;

public record EmergencyRoomMapInfo(
        Long emergencyRoomId,
        String hpId,
        String hospitalName,
        String address,
        String simpleMap,
        Coordinate coordinate,
        double distance) {

    @QueryProjection
    public EmergencyRoomMapInfo(
            Long emergencyRoomId,
            String hpId,
            String hospitalName,
            String address,
            String simpleMap,
            Point location,
            double distance) {
        this(
                emergencyRoomId,
                hpId,
                hospitalName,
                address,
                simpleMap,
                Coordinate.of(location.getX(), location.getY()),
                distance);
    }
}
