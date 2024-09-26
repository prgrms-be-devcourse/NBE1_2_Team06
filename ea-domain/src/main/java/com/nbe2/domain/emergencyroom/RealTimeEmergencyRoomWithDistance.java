package com.nbe2.domain.emergencyroom;

public record RealTimeEmergencyRoomWithDistance(
        RealTimeEmergencyInfo realTimeEmergencyInfo, double distance) {

    public static RealTimeEmergencyRoomWithDistance of(
            RealTimeEmergencyInfo realTimeEmergencyInfo, double distance) {
        return new RealTimeEmergencyRoomWithDistance(realTimeEmergencyInfo, distance);
    }
}
