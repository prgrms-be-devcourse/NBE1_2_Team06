package com.nbe2.infra.openapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbe2.domain.emergencyroom.EmergencyRoomInfo;

public record EmergencyRoomResponse(
        // 진료과목
        @JsonProperty("dgidIdName") String dgidIdName,

        // 기관ID
        @JsonProperty("hpid") String hpid,

        // 기관명
        @JsonProperty("dutyName") String dutyName,

        // 우편번호1
        @JsonProperty("postCdn1") String postCdn1,

        // 우편번호2
        @JsonProperty("postCdn2") String postCdn2,

        // 주소
        @JsonProperty("dutyAddr") String dutyAddr,

        // 대표전화1
        @JsonProperty("dutyTel1") String dutyTel1,

        // 응급실전화
        @JsonProperty("dutyTel3") String dutyTel3,

        // 응급실
        @JsonProperty("hvec") int hvec,

        // 수술실
        @JsonProperty("hvoc") int hvoc,

        // 신경중환자
        @JsonProperty("hvcc") int hvcc,

        // 신생중환자
        @JsonProperty("hvncc") int hvncc,

        // 흉부중환자
        @JsonProperty("hvccc") int hvccc,

        // 일반중환자
        @JsonProperty("hvicc") int hvicc,

        // 입원실
        @JsonProperty("hvgc") int hvgc,

        // 입원실가용여부(1/2)
        @JsonProperty("dutyHayn") int dutyHayn,

        // 병상수
        @JsonProperty("dutyHano") int dutyHano,

        // 기관설명상세
        @JsonProperty("dutyInf") String dutyInf,

        // 간이약도
        @JsonProperty("dutyMapimg") String dutyMapimg,

        // 응급실운영여부(1/2)
        @JsonProperty("dutyEryn") int dutyEryn,

        // 진료시간(월요일)C
        @JsonProperty("dutyTime1c") String dutyTime1c,

        // 진료시간(화요일)C
        @JsonProperty("dutyTime2c") String dutyTime2c,

        // 진료시간(수요일)C
        @JsonProperty("dutyTime3c") String dutyTime3c,

        // 진료시간(목요일)C
        @JsonProperty("dutyTime4c") String dutyTime4c,

        // 진료시간(금요일)C
        @JsonProperty("dutyTime5c") String dutyTime5c,

        // 진료시간(토요일)C
        @JsonProperty("dutyTime6c") String dutyTime6c,

        // 진료시간(일요일)C
        @JsonProperty("dutyTime7c") String dutyTime7c,

        // 진료시간(공휴일)C
        @JsonProperty("dutyTime8c") String dutyTime8c,

        // 진료시간(월요일)S
        @JsonProperty("dutyTime1s") String dutyTime1s,

        // 진료시간(화요일)S
        @JsonProperty("dutyTime2s") String dutyTime2s,

        // 진료시간(수요일)S
        @JsonProperty("dutyTime3s") String dutyTime3s,

        // 진료시간(목요일)S
        @JsonProperty("dutyTime4s") String dutyTime4s,

        // 진료시간(금요일)S
        @JsonProperty("dutyTime5s") String dutyTime5s,

        // 진료시간(토요일)S
        @JsonProperty("dutyTime6s") String dutyTime6s,

        // 진료시간(일요일)S
        @JsonProperty("dutyTime7s") String dutyTime7s,

        // 진료시간(공휴일)S
        @JsonProperty("dutyTime8s") String dutyTime8s,

        // 병원위도
        @JsonProperty("wgs84Lat") double wgs84Lat,

        // 병원경도
        @JsonProperty("wgs84Lon") double wgs84Lon,

        // 병상수
        @JsonProperty("hpbdn") int hpbdn,

        // 흉부중환자실
        @JsonProperty("hpccuyn") int hpccuyn,

        // 신경중환자실
        @JsonProperty("hpcuyn") int hpcuyn,

        // 응급실
        @JsonProperty("hperyn") int hperyn,

        // 입원실
        @JsonProperty("hpgryn") int hpgryn,

        // 일반중환자실
        @JsonProperty("hpicuyn") int hpicuyn,

        // 신생아중환자실
        @JsonProperty("hpnicuyn") int hpnicuyn,

        // 수술실
        @JsonProperty("hpopyn") int hpopyn) {

    public EmergencyRoomInfo toEmergencyRoomInfo() {
        return EmergencyRoomInfo.builder()
                .id(hpid)
                .hospitalName(dutyName)
                .zipCode(postCdn1)
                .address(dutyAddr)
                .mainContactNumber(dutyTel1)
                .emergencyRoomContactNumber(dutyTel3)
                .simpleMap(dutyMapimg)
                .emergencyRoomAvailability(convertorBoolean(dutyEryn))
                .longitude(String.valueOf(wgs84Lat))
                .latitude(String.valueOf(wgs84Lon))
                .medicalDepartments(dgidIdName)
                .totalBedCount(hpbdn)
                .thoracicIcuBedCount(hpccuyn)
                .neurologicalIcuBedCount(hpccuyn)
                .emergencyRoomBedCount(hperyn)
                .generalWardBedCount(hpgryn)
                .generalIcuBedCount(hpicuyn)
                .neonatalIcuBedCount(hpnicuyn)
                .operatingRoomBedCount(hpopyn)
                .build();
    }

    private boolean convertorBoolean(int value) {
        return value == 1;
    }
}
