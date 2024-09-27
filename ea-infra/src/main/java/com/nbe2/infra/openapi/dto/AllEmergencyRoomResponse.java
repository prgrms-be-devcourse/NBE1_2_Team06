package com.nbe2.infra.openapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AllEmergencyRoomResponse(

        // 주소
        @JsonProperty("dutyAddr") String dutyAddr,

        // 응급실 코드
        @JsonProperty("dutyEmcls") String dutyEmcls,

        // 응급실 이름
        @JsonProperty("dutyEmclsName") String dutyEmclsName,

        // 병원 이름
        @JsonProperty("dutyName") String dutyName,

        // 대표 전화
        @JsonProperty("dutyTel1") String dutyTel1,

        // 비상 전화
        @JsonProperty("dutyTel3") String dutyTel3,

        // 병원 ID
        @JsonProperty("hpid") String hpid,

        // 병원 ID (다른 명칭)
        @JsonProperty("phpid") String phpid,

        // 레코드 번호
        @JsonProperty("rnum") int rnum,

        // 위도
        @JsonProperty("wgs84Lat") double wgs84Lat,

        // 경도
        @JsonProperty("wgs84Lon") double wgs84Lon) {}
