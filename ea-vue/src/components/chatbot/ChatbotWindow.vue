<template>
  <div class="chat-container">
    <div class="chat-header">
      <button @click="$emit('closeChatbot')" class="close-button">
        <span class="fas fa-chevron-down"></span>
      </button>
    </div>
    <MessageList :messages="messages" :isLoading="isLoading"/>
    <MessageInput @sendMessage="handleSendMessage" />
  </div>
</template>

<script>
import { useSendingQuery } from '@/composables/chatbot-api';
import MessageList from './MessageList.vue';
import MessageInput from './MessageInput.vue';

export default {
  props: {
    messages: {
      type: Array,
      required: true,
    },
    sessionId: {
      type: String,
      required: true,
    },
  },
  components: {
    MessageList,
    MessageInput,
  },
  data() {
    return {
      isLoading: false,
    };
  },
  methods: {
    async handleSendMessage(message) {
      if (!this.sessionId) return;

      this.$emit('updateMessages', { text: message, sender: 'user' });
      this.isLoading = true;

      const data = await useSendingQuery(message, this.sessionId);

      this.isLoading = false;
      this.$emit('updateMessages', { text: data, sender: 'bot' });
    },
  },
};
</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  height: 100%;
  background-color: #f9f9f9;
}

.chat-header {
  display: flex;
  justify-content: flex-end;
  background-color: #007bff;
  padding: 10px;
}

.close-button {
  background-color: transparent;
  color: white;
  font-size: 18px;
  border: none;
  cursor: pointer;
}
</style>
