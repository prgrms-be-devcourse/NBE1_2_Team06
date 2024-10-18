import axios from 'axios';

async function useConnect() {
  const response = await axios.post('/api/chatbot/session');
  return response.data.result;
}

async function useSendingQuery(query, sessionId) {
  const response = await axios.post('/api/chatbot/query', {
    query,
    sessionId,
  });
  return response.data.result.answer;
}

async function useDisconnect(sessionId) {
  await axios.delete(`/api/chatbot/session?id=${sessionId}`);
}

export { useConnect, useSendingQuery, useDisconnect };
