package com.nbe2.infra.openapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbe2.domain.emergencyroom.RealTimeEmergencyInfo;

public record RealTimeEmergencyDataResponse(
        @JsonProperty("hpid") String hospitalId, // 병원 ID
        @JsonProperty("dutyName") String hospitalName, // 병원 이름
        @JsonProperty("dutyTel3") String emergencyPhone, // 응급실 전화번호
        @JsonProperty("hvidate") String inputDate, // 입력 일시
        @JsonProperty("hvec") int availableBeds, // 응급실 가용 병상 수
        @JsonProperty("hvoc") int operatingRoomBeds, // 수술실 가용 병상 수
        @JsonProperty("hvcc") int neuroIcuBeds, // 신경과 중환자실 가용 병상 수
        @JsonProperty("hvncc") int neonatalIcuBeds, // 신생아 중환자실 가용 병상 수
        @JsonProperty("hvccc") int chestIcuBeds, // 흉부외과 중환자실 가용 병상 수
        @JsonProperty("hvicc") int generalIcuBeds, // 일반 중환자실 가용 병상 수
        @JsonProperty("hvgc") int generalWardBeds, // 입원실 가용 병상 수
        @JsonProperty("hvctayn") String ctAvailable, // CT 가용 여부 (Y: 가능, N: 불가)
        @JsonProperty("hvmriayn") String mriAvailable, // MRI 가용 여부 (Y: 가능, N: 불가)
        @JsonProperty("hvangioayn") String angiographyAvailable, // 혈관촬영기 가용 여부 (Y: 가능, N: 불가)
        @JsonProperty("hvventiayn") String ventilatorAvailable, // 인공호흡기 가용 여부 (Y: 가능, N: 불가)
        @JsonProperty("hvincuayn") String incubatorAvailable, // 인큐베이터 가용 여부 (Y: 가능, N: 불가)
        @JsonProperty("hvamyn") String ambulanceAvailable // 구급차 가용 여부 (Y: 가능, N: 불가))
        ) {

    public RealTimeEmergencyInfo toRealTimeEmergencyInfo() {
        return new RealTimeEmergencyInfo(
                hospitalId,
                hospitalName,
                emergencyPhone,
                inputDate,
                availableBeds,
                operatingRoomBeds,
                neuroIcuBeds,
                neonatalIcuBeds,
                chestIcuBeds,
                generalIcuBeds,
                generalWardBeds,
                convertToBoolean(ctAvailable),
                convertToBoolean(mriAvailable),
                convertToBoolean(angiographyAvailable),
                convertToBoolean(ventilatorAvailable),
                convertToBoolean(incubatorAvailable),
                convertToBoolean(ambulanceAvailable));
    }

    private boolean convertToBoolean(String value) {
        return "Y".equalsIgnoreCase(value);
    }
}
