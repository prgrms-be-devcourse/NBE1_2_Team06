package com.nbe2.domain.emergencyroom;

import java.util.List;

public interface EmergencyRoomRepositoryCustom {

    List<EmergencyRoomMapInfo> findByCoordinateAndDistance(Coordinate coordinate, double distance);
}
