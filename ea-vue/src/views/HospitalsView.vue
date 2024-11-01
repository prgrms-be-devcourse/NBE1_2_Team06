<template>
  <div class="home">
    <div class="flex flex-col h-screen bg-gray-100">
      <header class="bg-white shadow-sm p-4 sticky top-0 z-10">
        <div class="flex items-center justify-between mb-2">
          <h1 class="text-xl font-bold">주변 응급실 현황</h1>
          <button @click="handleRefresh" class="h-10 w-10 flex items-center justify-center
          rounded-md border border-gray-300 text-gray-700 hover:bg-gray-100">
            <RefreshCcwIcon class="h-5 w-5" />
            <span class="sr-only">새로고침</span>
          </button>
        </div>
        <div class="flex items-center text-sm text-gray-500">
          <MapPinIcon class="h-4 w-4 mr-1" />
          <span>현재 위치: 서울특별시 강남구</span>
        </div>
      </header>
      <main class="flex-1 overflow-y-auto">
        <ChatbotView />
        <div v-if="isLoading" class="p-4 text-center text-gray-500">로딩 중...</div>
        <div v-else-if="error" class="p-4 text-center text-red-500">{{ error }}</div>
        <ul v-else class="divide-y divide-gray-200">
          <HospitalListItem
            v-for="hospital in hospitals"
            :key="hospital.id"
            :hospital="hospital"
          />
        </ul>
      </main>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import { defineComponent, ref, onMounted } from 'vue';
import { MapPin, RefreshCcw } from 'lucide-vue-next';
import ChatbotView from '@/views/ChatbotView.vue';
import HospitalListItem from '@/components/realtime-hospital/HospitalListItem.vue';
import { useHospitals } from '@/composables/useHospitals';

export default defineComponent({
  name: 'HomeView',
  components: {
    HospitalListItem,
    ChatbotView,
    MapPinIcon: MapPin,
    RefreshCcwIcon: RefreshCcw,
  },
  setup() {
    const longitude = ref(127.1086228);
    const latitude = ref(37.4012191);

    const {
      hospitals, isLoading, error, fetchHospitals,
    } = useHospitals();

    const handleRefresh = () => {
      fetchHospitals(longitude.value, latitude.value);
    };

    onMounted(() => {
      fetchHospitals(longitude.value, latitude.value);
    });

    return {
      hospitals,
      isLoading,
      error,
      handleRefresh,
    };
  },
});
</script>

<style scoped>
.home {
  position: relative;
}
</style>
