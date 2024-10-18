<template>
  <button class="floating-chat-button" @click="toggleChatbot">
    <i class="fas fa-first-aid"></i>
  </button>

  <div v-if="isChatbotVisible" class="overlay"
       @click="toggleChatbot"
       @keydown.enter="toggleChatbot"
       tabindex="0">
  </div>

  <div v-if="isChatbotVisible" class="chatbot-window">
    <ChatbotWindow
        :messages="messages"
        :sessionId="sessionId"
        @updateMessages="updateMessages"
        @closeChatbot="toggleChatbot"/>
  </div>
</template>

<script>
import ChatbotWindow from '@/components/chatbot/ChatbotWindow.vue';
import { useConnect, useDisconnect } from '@/composables/chatbot-api';
import formatChatMessage from '@/composables/format-chat-message';

export default {
  name: 'ChatbotView',
  components: {
    ChatbotWindow,
  },
  data() {
    return {
      isChatbotVisible: false,
      messages: [],
      sessionId: null,
    };
  },
  async mounted() {
    try {
      const response = await useConnect();
      this.sessionId = response.sessionId;

      this.messages.push({
        text: formatChatMessage('안녕하세요!\n저는 응급🚑 비서 **은비**입니다.'),
        sender: 'bot',
      });
      this.messages.push({
        text: formatChatMessage('응급 처치 방법에 대해서 질문해주세요!'),
        sender: 'bot',
      });
    } catch (error) {
      this.messages.push({
        text: formatChatMessage('문제가 발생했습니다.\n잠시 후 다시 시도해주세요!'),
        sender: 'bot',
      });
      console.log('Error on connect', error);
    }
  },
  methods: {
    toggleChatbot() {
      this.isChatbotVisible = !this.isChatbotVisible;
    },
    updateMessages({ text, sender }) {
      if (sender === 'user') {
        this.messages.push({ text: formatChatMessage(text), sender });
        return;
      }

      this.messages.push({ text: '', sender });
      this.typeCharacter(this.messages.length - 1, text, 0);
    },
    typeCharacter(messageIndex, fullText, index) {
      if (index >= fullText.length) return;

      this.messages[messageIndex].text += fullText[index];
      this.messages[messageIndex].text = formatChatMessage(this.messages[messageIndex].text);
      setTimeout(() => {
        this.typeCharacter(messageIndex, fullText, index + 1);
      }, 40);
    },
  },
  beforeUnmount() {
    if (this.sessionId) {
      useDisconnect(this.sessionId).catch((error) => console.log('Error on disconnect', error));
    }
  },
};
</script>

<style scoped>
.floating-chat-button {
  position: fixed;
  bottom: 35px;
  right: 35px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 50%;
  width: 70px;
  height: 70px;
  font-size: 28px;
  cursor: pointer;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  transition: background-color 0.3s;
}

.floating-chat-button:hover {
  background-color: #0056b3;
}

.chatbot-window {
  position: fixed;
  bottom: 30px;
  right: 20px;
  width: 350px;
  height: 85%;
  background-color: white;
  box-shadow: 0 0px 10px rgba(0, 0, 0, 0.2);
  border-radius: 10px;
  overflow: hidden;
  z-index: 1001;
  transition: all 0.3s ease;
}

@media (max-width: 600px) {
  .chatbot-window {
    width: 100vw;
    height: 100vh;
    border-radius: 0;
    bottom: 0;
    right: 0;
  }
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 1000;
}
</style>
