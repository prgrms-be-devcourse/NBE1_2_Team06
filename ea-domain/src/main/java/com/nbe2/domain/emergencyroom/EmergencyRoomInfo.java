package com.nbe2.domain.emergencyroom;

import lombok.Builder;

@Builder
public record EmergencyRoomInfo(
        String id,
        String hospitalName,
        String zipCode,
        String address,
        String mainContactNumber,
        String emergencyRoomContactNumber,
        String simpleMap,
        boolean emergencyRoomAvailability,
        String longitude,
        String latitude,
        String medicalDepartments,
        int totalBedCount,
        int thoracicIcuBedCount,
        int neurologicalIcuBedCount,
        int emergencyRoomBedCount,
        int generalWardBedCount,
        int generalIcuBedCount,
        int neonatalIcuBedCount,
        int operatingRoomBedCount) {

    public EmergencyRoom toEmergencyRoom() {

        Coordinate coordinate;

        if (Double.parseDouble(longitude) < 90.0) {
            coordinate = Coordinate.of(Double.parseDouble(latitude), Double.parseDouble(longitude));
        } else {
            coordinate = Coordinate.of(Double.parseDouble(longitude), Double.parseDouble(latitude));
        }

        return EmergencyRoom.builder()
                .hpId(id)
                .hospitalName(hospitalName)
                .zipCode(zipCode)
                .address(address)
                .mainContactNumber(mainContactNumber)
                .emergencyRoomContactNumber(emergencyRoomContactNumber)
                .simpleMap(simpleMap)
                .emergencyRoomAvailability(emergencyRoomAvailability)
                .coordinate(coordinate)
                .medicalDepartments(medicalDepartments)
                .totalBedCount(totalBedCount)
                .thoracicIcuBedCount(thoracicIcuBedCount)
                .neurologicalIcuBedCount(neurologicalIcuBedCount)
                .emergencyRoomBedCount(emergencyRoomBedCount)
                .generalWardBedCount(generalWardBedCount)
                .generalIcuBedCount(generalIcuBedCount)
                .neonatalIcuBedCount(neonatalIcuBedCount)
                .operatingRoomBedCount(operatingRoomBedCount)
                .build();
    }
}
