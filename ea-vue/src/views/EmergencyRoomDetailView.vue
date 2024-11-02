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

      <!-- 공지사항 및 리뷰 탭 -->
      <div class="bg-white shadow-md rounded-lg p-6 space-y-4">
        <div class="flex border-b border-gray-200">
          <button @click="activeTab = '공지사항'" :class="{'border-b-2 border-blue-500 text-blue-600': activeTab === '공지사항'}" class="pb-2 px-4 font-medium text-gray-500">공지사항</button>
          <button @click="activeTab = '리뷰'" :class="{'border-b-2 border-blue-500 text-blue-600': activeTab === '리뷰'}" class="pb-2 px-4 font-medium text-gray-500">리뷰</button>
        </div>

        <!-- 공지사항 탭 내용 -->
        <div v-if="activeTab === '공지사항'" class="space-y-4">
          <div v-for="(notice, index) in notices" :key="index" class="bg-gray-100 p-4 rounded-lg space-y-2">
            <h3 class="text-lg font-semibold text-gray-800">{{ notice.title }}</h3>
            <p class="text-gray-600">{{ notice.description }}</p>
            <span class="text-sm text-gray-400">{{ notice.date }}</span>
          </div>
        </div>

        <!-- 리뷰 탭 내용 -->
        <div v-if="activeTab === '리뷰'" class="space-y-4">
          <div v-for="(review, index) in reviews" :key="index" class="bg-gray-100 p-4 rounded-lg space-y-2">
            <div class="flex justify-between items-center">
              <h3 class="text-lg font-semibold text-gray-800">{{ review.reviewer }}</h3>
              <span class="text-sm text-gray-400">{{ review.date }}</span>
            </div>
            <p class="text-gray-600">{{ review.comment }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, computed, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useEmergencyRoomDetail } from '@/composables/useEmergencyRoomDetail';
import { ArrowLeftIcon, MapPinIcon, PhoneIcon, UserIcon } from '@heroicons/vue/20/solid';

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
    const activeTab = ref('공지사항');

    const departmentList = computed(() => {
      return emergencyRoomDetail.value?.medicalDepartments?.split(',') || [];
    });

    const notices = [
      { title: "코로나19 백신 접종 안내", description: "다음 주부터 65세 이상 어르신들을 대상으로 코로나19 백신 접종을 시작합니다. 예약은 병원 홈페이지나 전화로 가능합니다.", date: "2023-09-25" },
      { title: "응급실 리모델링 공사 안내", description: "10월 1일부터 2주간 응급실 리모델링 공사로 인해 일시적으로 응급실 이용이 제한될 수 있습니다. 양해 부탁드립니다.", date: "2023-09-20" }
    ];

    const reviews = [
      { reviewer: "응급환자1", date: "2023-09-24", comment: "신속한 대응과 친절한 서비스에 감사드립니다." },
      { reviewer: "응급환자2", date: "2023-09-23", comment: "응대가 조금 더 빨랐으면 좋겠어요." }
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
      notices,
      reviews,
      activeTab
    };
  },
};
</script>

<style scoped>
.material-icons {
  font-size: 1.2rem;
}
</style>
