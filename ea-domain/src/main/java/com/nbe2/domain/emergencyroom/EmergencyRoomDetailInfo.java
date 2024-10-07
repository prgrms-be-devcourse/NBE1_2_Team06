package com.nbe2.domain.emergencyroom;

public record EmergencyRoomDetailInfo(
        String hospitalName,
        String address,
        String emergencyRoomContactNumber,
        String medicalDepartments,
        int emergencyRoomBedCount,
        // 실시간 정보
        int availableBeds,
        int operatingRoomBeds,
        boolean isCtAvailable,
        boolean isMriAvailable,
        boolean isAngiographyAvailable,
        boolean isVentilatorAvailable,
        boolean isIncubatorAvailable,
        boolean isAmbulanceAvailable,
        double distance) {
    public static EmergencyRoomDetailInfo create(
            EmergencyRoom emergencyRoom,
            RealTimeEmergencyRoomWithDistance realTimeEmergencyRoomWithDistance) {
        return new EmergencyRoomDetailInfo(
                emergencyRoom.getHospitalName(),
                emergencyRoom.getAddress(),
                emergencyRoom.getEmergencyRoomContactNumber(),
                emergencyRoom.getMedicalDepartments(),
                emergencyRoom.getEmergencyRoomBedCount(),
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo().availableBeds(),
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo().operatingRoomBeds(),
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo().isCtAvailable(),
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo().isMriAvailable(),
                realTimeEmergencyRoomWithDistance
                        .realTimeEmergencyRoomInfo()
                        .isAngiographyAvailable(),
                realTimeEmergencyRoomWithDistance
                        .realTimeEmergencyRoomInfo()
                        .isVentilatorAvailable(),
                realTimeEmergencyRoomWithDistance
                        .realTimeEmergencyRoomInfo()
                        .isIncubatorAvailable(),
                realTimeEmergencyRoomWithDistance
                        .realTimeEmergencyRoomInfo()
                        .isAmbulanceAvailable(),
                realTimeEmergencyRoomWithDistance.distance());
    }
}
