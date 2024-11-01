<template>
  <div v-if="emergencyRoomDetail">
    <h1>{{ emergencyRoomDetail.hospitalName }}</h1>
    <p>주소: {{ emergencyRoomDetail.address }}</p>
    <p>연락처: {{ emergencyRoomDetail.emergencyRoomContactNumber }}</p>
    <p>진료 과목: {{ emergencyRoomDetail.medicalDepartments }}</p>
    <p>응급실 병상 수: {{ emergencyRoomDetail.emergencyRoomBedCount }}</p>
    <h2>실시간 정보</h2>
    <p>가용 병상 수: {{ emergencyRoomDetail.availableBeds }}</p>
    <p>수술실 병상 수: {{ emergencyRoomDetail.operatingRoomBeds }}</p>
    <p>CT 가능 여부: {{ emergencyRoomDetail.isCtAvailable ? '가능' : '불가' }}</p>
    <p>MRI 가능 여부: {{ emergencyRoomDetail.isMriAvailable ? '가능' : '불가' }}</p>
    <p>혈관 조영술 가능 여부: {{ emergencyRoomDetail.isAngiographyAvailable ? '가능' : '불가' }}</p>
    <p>환기기 가능 여부: {{ emergencyRoomDetail.isVentilatorAvailable ? '가능' : '불가' }}</p>
    <p>인큐베이터 가능 여부: {{ emergencyRoomDetail.isIncubatorAvailable ? '가능' : '불가' }}</p>
    <p>구급차 대기 여부: {{ emergencyRoomDetail.isAmbulanceAvailable ? '가능' : '불가' }}</p>
    <p>거리: {{ emergencyRoomDetail.distance }} km</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      emergencyRoomDetail: null,
    };
  },
  props: {
    hospitalId: {
      type: String,
      required: true,
    },
    latitude: {
      type: Number,
      required: true,
    },
    longitude: {
      type: Number,
      required: true,
    },
  },
  methods: {
    async fetchEmergencyRoomDetail() {
      try {
        const response = await axios.get(
          `/api/emergency-room/${this.hospitalId}`,
          {
            params: {
              latitude: this.latitude,
              longitude: this.longitude,
            },
          }
        );
        this.emergencyRoomDetail = response.data;
      } catch (error) {
        console.error("응급실 상세 정보를 가져오는 데 실패했습니다.", error);
      }
    },
  },
  mounted() {
    this.fetchEmergencyRoomDetail();
  },
};
</script>
