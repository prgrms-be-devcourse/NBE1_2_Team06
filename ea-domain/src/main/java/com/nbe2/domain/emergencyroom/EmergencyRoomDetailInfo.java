package com.nbe2.domain.emergencyroom;

public record EmergencyRoomDetailInfo(
        String hospitalName,
        String address,
        String emergencyRoomContactNumber,
        String medicalDepartments,
        int emergencyRoomBedCount) {
    public static EmergencyRoomDetailInfo from(EmergencyRoom emergencyRoom) {
        return new EmergencyRoomDetailInfo(
                emergencyRoom.getHospitalName(),
                emergencyRoom.getAddress(),
                emergencyRoom.getEmergencyRoomContactNumber(),
                emergencyRoom.getMedicalDepartments(),
                emergencyRoom.getEmergencyRoomBedCount());
    }
}
