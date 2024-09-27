package com.nbe2.domain.emergencyroom;

public record RealTimeEmergencyRoomWithDistance(
        RealTimeEmergencyRoomInfo realTimeEmergencyRoomInfo, double distance) {

    public static RealTimeEmergencyRoomWithDistance of(
            RealTimeEmergencyRoomInfo realTimeEmergencyRoomInfo, double distance) {
        return new RealTimeEmergencyRoomWithDistance(realTimeEmergencyRoomInfo, distance);
    }
}
