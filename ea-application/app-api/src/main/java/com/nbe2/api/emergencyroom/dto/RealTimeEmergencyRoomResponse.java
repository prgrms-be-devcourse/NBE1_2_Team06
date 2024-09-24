package com.nbe2.api.emergencyroom.dto;

import com.nbe2.domain.emergencyroom.RealTimeEmergencyInfo;

public record RealTimeEmergencyRoomResponse(
        String hospitalId, // 병원 ID
        String hospitalName, // 병원 이름
        String emergencyPhone, // 응급실 전화번호
        String inputDate, // 입력 일시
        int availableBeds, // 응급실 가용 병상 수
        int operatingRoomBeds, // 수술실 가용 병상 수
        int neuroIcuBeds, // 신경과 중환자실 가용 병상 수
        int neonatalIcuBeds, // 신생아 중환자실 가용 병상 수
        int chestIcuBeds, // 흉부외과 중환자실 가용 병상 수
        int generalIcuBeds, // 일반 중환자실 가용 병상 수
        int generalWardBeds, // 입원실 가용 병상 수
        boolean isCtAvailable, // CT 가용 여부 (Y: 가능, N: 불가)
        boolean isMriAvailable, // MRI 가용 여부 (Y: 가능, N: 불가)
        boolean isAngiographyAvailable, // 혈관촬영기 가용 여부 (Y: 가능, N: 불가)
        boolean isVentilatorAvailable, // 인공호흡기 가용 여부 (Y: 가능, N: 불가)
        boolean isIncubatorAvailable, // 인큐베이터 가용 여부 (Y: 가능, N: 불가)
        boolean isAmbulanceAvailable // 구급차 가용 여부 (Y: 가능, N: 불가))
        ) {

    public static RealTimeEmergencyRoomResponse from(RealTimeEmergencyInfo realTimeEmergencyInfo) {
        return new RealTimeEmergencyRoomResponse(
                realTimeEmergencyInfo.hospitalId(),
                realTimeEmergencyInfo.hospitalName(),
                realTimeEmergencyInfo.emergencyPhone(),
                realTimeEmergencyInfo.inputDate(),
                realTimeEmergencyInfo.availableBeds(),
                realTimeEmergencyInfo.operatingRoomBeds(),
                realTimeEmergencyInfo.neuroIcuBeds(),
                realTimeEmergencyInfo.neonatalIcuBeds(),
                realTimeEmergencyInfo.chestIcuBeds(),
                realTimeEmergencyInfo.generalIcuBeds(),
                realTimeEmergencyInfo.generalWardBeds(),
                realTimeEmergencyInfo.isCtAvailable(),
                realTimeEmergencyInfo.isMriAvailable(),
                realTimeEmergencyInfo.isAngiographyAvailable(),
                realTimeEmergencyInfo.isVentilatorAvailable(),
                realTimeEmergencyInfo.isIncubatorAvailable(),
                realTimeEmergencyInfo.isAmbulanceAvailable());
    }
}
