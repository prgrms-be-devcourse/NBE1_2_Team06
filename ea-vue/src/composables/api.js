import axios from 'axios';

// 임시로 api 파일에 api fetch 함수를 몰아넣었는데
// 이것도 도메인 별로 파일을 분리하면 깔끔할듯

async function useHealthCheck() {
  const response = await axios.get('http://localhost:8080/health');
  return response.data;
}

async function useUseProfile() {
  const response = await axios.get('/api/my', {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('token')}`,
    },
  });
  return response.data;
}

export { useHealthCheck, useUseProfile };
