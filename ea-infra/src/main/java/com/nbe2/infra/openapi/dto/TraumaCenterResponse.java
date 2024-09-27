package com.nbe2.infra.openapi.dto;

import lombok.Builder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbe2.domain.emergencyroom.EmergencyRoomInfo;

@Builder
public record TraumaCenterResponse(

        // 기관 ID
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
        @JsonProperty("hvec") String hvec,

        // 수술실
        @JsonProperty("hvoc") String hvoc,

        // 신경중환자
        @JsonProperty("hvcc") String hvcc,

        // 신생중환자
        @JsonProperty("hvncc") String hvncc,

        // 흉부중환자
        @JsonProperty("hvccc") String hvccc,

        // 일반중환자
        @JsonProperty("hvicc") String hvicc,

        // 입원실
        @JsonProperty("hvgc") String hvgc,

        // 입원실 가용 여부(1/2)
        @JsonProperty("dutyHayn") String dutyHayn,

        // 병상수
        @JsonProperty("dutyHano") String dutyHano,

        // 기관설명 상세
        @JsonProperty("dutyInf") String dutyInf,

        // 간이 약도
        @JsonProperty("dutyMapimg") String dutyMapimg,

        // 응급실 운영 여부(1/2)
        @JsonProperty("dutyEryn") String dutyEryn,

        // 진료 시간(월요일) C
        @JsonProperty("dutyTime1c") String dutyTime1c,

        // 진료 시간(화요일) C
        @JsonProperty("dutyTime2c") String dutyTime2c,

        // 진료 시간(수요일) C
        @JsonProperty("dutyTime3c") String dutyTime3c,

        // 진료 시간(목요일) C
        @JsonProperty("dutyTime4c") String dutyTime4c,

        // 진료 시간(금요일) C
        @JsonProperty("dutyTime5c") String dutyTime5c,

        // 진료 시간(토요일) C
        @JsonProperty("dutyTime6c") String dutyTime6c,

        // 진료 시간(일요일) C
        @JsonProperty("dutyTime7c") String dutyTime7c,

        // 진료 시간(공휴일) C
        @JsonProperty("dutyTime8c") String dutyTime8c,

        // 진료 시간(월요일) S
        @JsonProperty("dutyTime1s") String dutyTime1s,

        // 진료 시간(화요일) S
        @JsonProperty("dutyTime2s") String dutyTime2s,

        // 진료 시간(수요일) S
        @JsonProperty("dutyTime3s") String dutyTime3s,

        // 진료 시간(목요일) S
        @JsonProperty("dutyTime4s") String dutyTime4s,

        // 진료 시간(금요일) S
        @JsonProperty("dutyTime5s") String dutyTime5s,

        // 진료 시간(토요일) S
        @JsonProperty("dutyTime6s") String dutyTime6s,

        // 진료 시간(일요일) S
        @JsonProperty("dutyTime7s") String dutyTime7s,

        // 진료 시간(공휴일) S
        @JsonProperty("dutyTime8s") String dutyTime8s,

        // 응급실 (Emergency)
        @JsonProperty("MKioskTy25") String MKioskTy25,

        // 뇌출혈 수술
        @JsonProperty("MKioskTy1") String MKioskTy1,

        // 뇌경색의 재관류
        @JsonProperty("MKioskTy2") String MKioskTy2,

        // 심근경색의 재관류
        @JsonProperty("MKioskTy3") String MKioskTy3,

        // 복부 손상의 수술
        @JsonProperty("MKioskTy4") String MKioskTy4,

        // 사지 접합의 수술
        @JsonProperty("MKioskTy5") String MKioskTy5,

        // 응급 내시경
        @JsonProperty("MKioskTy6") String MKioskTy6,

        // 응급 투석
        @JsonProperty("MKioskTy7") String MKioskTy7,

        // 조산 산모
        @JsonProperty("MKioskTy8") String MKioskTy8,

        // 정신질환자
        @JsonProperty("MKioskTy9") String MKioskTy9,

        // 신생아
        @JsonProperty("MKioskTy10") String MKioskTy10,

        // 중증 화상
        @JsonProperty("MKioskTy11") String MKioskTy11,

        // 병원 경도
        @JsonProperty("wgs84Lon") double wgs84Lon,

        // 병원 위도
        @JsonProperty("wgs84Lat") double wgs84Lat,

        // 진료 과목
        @JsonProperty("dgidIdName") String dgidIdName,

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

    private boolean convertorBoolean(String value) {
        return "Y".equalsIgnoreCase(value);
    }
}
