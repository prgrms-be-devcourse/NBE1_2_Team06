<template>
  <li class="bg-white rounded-lg p-5 transition-all duration-200 hover:shadow-md">
    <div class="flex justify-between items-start gap-4">
      <!-- 병원 정보 섹션 -->
      <div class="flex-1">
        <div class="flex items-center gap-2">
          <h2 class="text-lg font-bold text-gray-900">{{ hospital.name }}</h2>
          <span
            class="px-2 py-0.5 text-xs font-medium rounded-full"
            :class="getStatusBadgeClass(hospital.availableBeds)"
          >
            {{ getStatusText(hospital.availableBeds) }}
          </span>
        </div>

        <!-- 거리 및 진료과 정보 -->
        <div class="mt-2 space-y-1.5">
          <div class="flex items-center text-gray-600">
            <MapPinIcon class="h-4 w-4 mr-1.5 flex-shrink-0" />
            <span class="text-sm">
              {{ formatDistance(hospital.distance) }} 거리
            </span>
          </div>

          <!-- 업데이트 시간 정보 -->
          <div class="flex items-center text-gray-600">
            <ClockIcon class="h-4 w-4 mr-1.5 flex-shrink-0" />
            <span class="text-sm text-gray-500">{{ timeSinceUpdate(hospital.inputDate) }}</span>
          </div>
        </div>

        <!-- 병상 현황 상세 정보 -->
        <div class="mt-3 flex items-center gap-4">
          <div class="flex items-center gap-2">
            <div class="text-sm font-medium text-gray-700">가용 병상</div>
            <div
              class="text-lg font-bold"
              :class="getStatusColor(hospital.availableBeds)"
            >
              {{ hospital.availableBeds }}
            </div>
          </div>

          <!-- 병상 상태 프로그레스 바 -->
          <div class="flex-1 max-w-[200px]">
            <div class="h-2 bg-gray-100 rounded-full overflow-hidden">
              <div
                class="h-full transition-all duration-300"
                :class="getProgressBarClass(hospital.availableBeds)"
                :style="{ width: getProgressWidth(hospital.availableBeds) }"
              ></div>
            </div>
          </div>
        </div>
      </div>

      <!-- 전화 버튼 -->
      <button
        @click="handleCall"
        class="flex-shrink-0 flex items-center justify-center w-12 h-12 rounded-full
        bg-blue-50 text-blue-600 hover:bg-blue-100 transition-colors duration-200"
      >
        <PhoneIcon class="h-5 w-5" />
        <span class="sr-only">전화하기</span>
      </button>
    </div>
  </li>
</template>

<script>
import { defineComponent } from 'vue';
import { MapPin, Phone, Clock } from 'lucide-vue-next';

export default defineComponent({
  name: 'HospitalListItem',
  components: {
    MapPinIcon: MapPin,
    PhoneIcon: Phone,
    ClockIcon: Clock,
  },
  props: {
    hospital: {
      type: Object,
      required: true,
    },
  },
  methods: {
    getStatusColor(beds) {
      if (beds > 10) return 'text-green-600';
      if (beds > 0) return 'text-yellow-600';
      return 'text-red-600';
    },
    getStatusBadgeClass(beds) {
      if (beds > 10) return 'bg-green-50 text-green-700';
      if (beds > 0) return 'bg-yellow-50 text-yellow-700';
      return 'bg-red-50 text-red-700';
    },
    getProgressBarClass(beds) {
      if (beds > 10) return 'bg-green-500';
      if (beds > 0) return 'bg-yellow-500';
      return 'bg-red-500';
    },
    getStatusText(beds) {
      if (beds > 10) return '여유';
      if (beds > 0) return '혼잡';
      return '불가';
    },
    getProgressWidth(beds) {
      const percentage = Math.min((beds / 60) * 100, 100);
      if (beds < 0) {
        return '0%';
      }
      return `${percentage}%`;
    },
    formatDistance(distance) {
      return `${distance.toFixed(1)}km`;
    },
    handleCall() {
      console.log('Calling hospital:', this.hospital.name);
    },
    timeSinceUpdate(inputDate) {
      // inputDate를 Date 객체로 변환
      const year = parseInt(inputDate.substring(0, 4), 10);
      const month = parseInt(inputDate.substring(4, 6), 10) - 1; // JavaScript의 월은 0부터 시작
      const day = parseInt(inputDate.substring(6, 8), 10);
      const hours = parseInt(inputDate.substring(8, 10), 10);
      const minutes = parseInt(inputDate.substring(10, 12), 10);
      const seconds = parseInt(inputDate.substring(12, 14), 10);

      const updatedDate = new Date(year, month, day, hours, minutes, seconds);
      const now = new Date();
      const diffMs = now - updatedDate;
      const diffMinutes = Math.floor(diffMs / (1000 * 60));

      if (diffMinutes < 1) return '방금 전';
      if (diffMinutes < 60) return `${diffMinutes}분 전`;

      const diffHours = Math.floor(diffMinutes / 60);
      if (diffHours < 24) return `${diffHours}시간 전`;

      const diffDays = Math.floor(diffHours / 24);
      return `${diffDays}일 전`;
    },
  },
});
</script>
