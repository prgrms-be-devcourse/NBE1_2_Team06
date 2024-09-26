package com.nbe2.domain.emergencyroom;

public record EmergencyRoomInfo(
        Long id,
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
        int operatingRoomBedCount) {}
