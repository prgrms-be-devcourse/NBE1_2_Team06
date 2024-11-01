import { ref } from 'vue';
import axios from 'axios';

function useEmergencyRoomDetail() {
  const emergencyRoomDetail = ref(null);
  const isLoading = ref(false);
  const error = ref(null);

  const fetchEmergencyRoomDetail = async (hospitalId, longitude, latitude) => {
    isLoading.value = true;
    error.value = null;
    try {
      const response = await axios.get(`http://localhost:8080/api/v1/emergency-rooms/${hospitalId}`, {
        params: {
          longitude,
          latitude,
        },
      });
      if (response.data && response.data.responseCode === 'SUCCESS') {
        emergencyRoomDetail.value = response.data.result;
      } else {
        error.value = '응급실 정보를 가져오지 못했습니다.';
      }
    } catch (err) {
      error.value = err.message || '에러가 발생했습니다.';
    } finally {
      isLoading.value = false;
    }
  };

  return {
    emergencyRoomDetail,
    isLoading,
    error,
    fetchEmergencyRoomDetail,
  };
}

export { useEmergencyRoomDetail };
