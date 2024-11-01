import { ref } from 'vue';
import axios from 'axios';

function useHospitals() {
  const hospitals = ref([]);
  const isLoading = ref(false);
  const error = ref(null);

  const fetchHospitals = async (longitude, latitude) => {
    isLoading.value = true;
    error.value = null;
    try {
      const response = await axios.get('http://localhost:8080/api/v1/emergency-rooms/real-time', {
        params: {
          longitude,
          latitude,
        },
      });
      if (response.data && response.data.responseCode === 'SUCCESS') {
        hospitals.value = response.data.result.map((item, index) => ({
          id: item.hospitalId || index,
          name: item.hospitalName,
          distance: item.distance,
          availableBeds: item.availableBeds,
          emergencyPhone: item.emergencyPhone,
          inputDate: item.inputDate,
          specialty: item.specialty,
        }));
      } else {
        error.value = '병원 정보를 가져오지 못했습니다.';
      }
    } catch (err) {
      error.value = err.message || '에러가 발생했습니다.';
    } finally {
      isLoading.value = false;
    }
  };

  return {
    hospitals,
    isLoading,
    error,
    fetchHospitals,
  };
}

export { useHospitals };
