<template>
  <div class="chat-container">
    <MessageList :messages="messages" :isLoading="isLoading"/>
    <MessageInput @sendMessage="handleSendMessage" />
  </div>
</template>

<script>
import { useConnect, useSendingQuery, useDisconnect } from '@/composables/chatbot-api';
import MessageList from '../components/chatbot/MessageList.vue';
import MessageInput from '../components/chatbot/MessageInput.vue';

export default {
  components: {
    MessageList,
    MessageInput,
  },
  data() {
    return {
      messages: [],
      sessionId: null,
      isLoading: false,
    };
  },
  async mounted() {
    try {
      const response = await useConnect();
      this.sessionId = response.sessionId;
      this.messages.push({ text: this.formatMessage('안녕하세요! 응급 비서 은비입니다.\n응급 처치에 대해 질문하시면 답변 드릴 수 있습니다😇'), sender: 'bot' });
      console.log(this.sessionId);
    } catch (error) {
      console.log('Error on connect', error);
    }
  },
  methods: {
    async handleSendMessage(message) {
      if (!this.sessionId) {
        console.log('Session ID undefined');
        return;
      }

      this.messages.push({ text: this.formatMessage(message), sender: 'user' });
      this.isLoading = true;
      const data = await useSendingQuery(message, this.sessionId);
      this.isLoading = false;
      this.messages.push({ text: this.formatMessage(data), sender: 'bot' });
    },
    formatMessage(message) {
      return message.replace(/\n/g, '<br />').replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>');
    },
  },
  beforeUnmount() {
    if (this.sessionId) {
      useDisconnect(this.sessionId)
        .then(() => console.log('disconnected'))
        .catch((error) => console.log('Error on disconnect', error));
    }
  },
};
</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  height: 85vh;
  background-color: #f9f9f9;
}

.loading-indicator {
  text-align: center;
  padding: 10px;
  font-size: 16px;
  color: #666;
}

@media (max-width: 600px) {
  .chat-container {
    padding: 10px;
  }
}
</style>
