<template>
  <div class="p-6 bg-gray-50 min-h-screen space-y-6 relative">
    <!-- 뒤로가기 버튼 (Heroicons 사용) -->
    <button @click="goBack" class="absolute top-4 left-4 text-gray-600 hover:text-gray-800 flex items-center">
      <ArrowLeftIcon class="w-5 h-5 mr-1" />
      <span class="sr-only">뒤로가기</span>
    </button>

    <div v-if="isLoading" class="flex items-center justify-center h-screen text-gray-500">
      <span class="text-lg font-semibold">로딩 중...</span>
    </div>
    <div v-else-if="error" class="flex items-center justify-center h-screen text-red-500">
      <span class="text-lg font-semibold">{{ error }}</span>
    </div>
    <div v-else-if="emergencyRoomDetail" class="space-y-6">
      <div class="flex flex-col md:flex-row gap-6">
        <!-- 병원 기본 정보 카드 -->
        <div class="bg-white shadow-md rounded-lg p-6 flex-1 space-y-4">
          <div class="flex justify-between items-center">
            <h1 class="text-2xl font-bold text-gray-800">{{ emergencyRoomDetail.hospitalName }}</h1>
            <span class="text-gray-500 text-sm">{{ emergencyRoomDetail.distance }} km</span>
          </div>
          <p class="text-gray-600 flex items-center">
            <MapPinIcon class="w-5 h-5 text-gray-500 mr-2" />
            {{ emergencyRoomDetail.address }}
          </p>
          <p class="text-gray-600 flex items-center">
            <PhoneIcon class="w-5 h-5 text-gray-500 mr-2" />
            {{ emergencyRoomDetail.emergencyRoomContactNumber }}
          </p>
          <button class="w-full bg-blue-500 text-white px-4 py-2 rounded shadow hover:bg-blue-600 mt-4 flex items-center justify-center">
            <ArrowLeftIcon class="w-5 h-5 mr-1" /> 길찾기
          </button>
        </div>

        <!-- 병상 및 응급차 가용 현황 카드 -->
        <div class="bg-white shadow-md rounded-lg p-6 flex-1 space-y-4">
          <h2 class="text-lg font-semibold text-gray-800">병상 및 응급차 가용 현황</h2>
          <div class="space-y-2">
            <div class="flex justify-between items-center">
              <span class="flex items-center">
                <UserIcon class="w-5 h-5 text-gray-500 mr-2" /> 성인 병상
              </span>
              <span class="text-gray-500">{{ emergencyRoomDetail.availableBeds }} / {{ emergencyRoomDetail.emergencyRoomBedCount }}</span>
            </div>
            <div class="w-full bg-gray-200 rounded-full h-2">
              <div class="bg-blue-500 h-2 rounded-full" :style="{ width: (emergencyRoomDetail.availableBeds / emergencyRoomDetail.emergencyRoomBedCount) * 100 + '%' }"></div>
            </div>
          </div>
        </div>
      </div>

      <!-- 진료 과목 카드 -->
      <div class="bg-white shadow-md rounded-lg p-6 space-y-4">
        <h2 class="text-lg font-semibold text-gray-800">진료 가능 과목</h2>
        <div class="flex flex-wrap gap-2">
          <span v-for="(department, index) in departmentList" :key="index" class="bg-blue-100 text-blue-800 text-sm px-3 py-1 rounded-full">
            {{ department }}
          </span>
        </div>
      </div>

      <!-- 장비 현황 카드 -->
      <div class="bg-white shadow-md rounded-lg p-6 space-y-4">
        <h2 class="text-lg font-semibold text-gray-800">장비 현황</h2>
        <div class="flex flex-wrap gap-4">
          <span v-if="emergencyRoomDetail.isCtAvailable" class="bg-blue-100 text-blue-800 px-3 py-1 rounded-full flex items-center">
            <ArrowLeftIcon class="w-5 h-5 mr-2 text-blue-600" /> CT 가능
          </span>
          <span v-if="emergencyRoomDetail.isMriAvailable" class="bg-blue-100 text-blue-800 px-3 py-1 rounded-full flex items-center">
            <ArrowLeftIcon class="w-5 h-5 mr-2 text-blue-600" /> MRI 가능
          </span>
        </div>
      </div>

      <!-- 공지사항 카드 -->
      <div class="bg-white shadow-md rounded-lg p-6 space-y-4">
        <h2 class="text-lg font-semibold text-gray-800">병원 공지사항</h2>
        <ul class="space-y-2">
          <li v-for="(notice, index) in notices" :key="index" class="text-blue-500 underline cursor-pointer">
            {{ notice }}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useEmergencyRoomDetail } from '@/composables/useEmergencyRoomDetail';
import { ArrowLeftIcon, MapPinIcon, PhoneIcon, UserIcon } from '@heroicons/vue/20/solid'; // Heroicons에서 필요한 아이콘들 가져오기

export default {
  name: 'EmergencyRoomDetailView',
  components: {
    ArrowLeftIcon,
    MapPinIcon,
    PhoneIcon,
    UserIcon,
  },
  setup() {
    const route = useRoute();
    const router = useRouter();

    const { emergencyRoomDetail, isLoading, error, fetchEmergencyRoomDetail } = useEmergencyRoomDetail();

    const departmentList = computed(() => {
      return emergencyRoomDetail.value?.medicalDepartments?.split(',') || [];
    });

    const notices = [
      "코로나19 선별진료소 운영 안내",
      "응급실 리모델링 공사 안내"
    ];

    onMounted(() => {
      const hospitalId = route.params.id;
      const longitude = 127.1086228;
      const latitude = 37.4012191;
      fetchEmergencyRoomDetail(hospitalId, longitude, latitude);
    });

    const goBack = () => {
      router.back();
    };

    return {
      emergencyRoomDetail,
      isLoading,
      error,
      goBack,
      departmentList,
      notices
    };
  },
};
</script>

<style scoped>
.material-icons {
  font-size: 1.2rem;
}
</style>
