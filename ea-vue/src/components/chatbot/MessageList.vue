<template>
  <div class="message-list" ref="messageList">
    <div v-for="(message, index) in messages" :key="index" :class="getMessageClass(message)">
      <span v-html="message.text"></span>
    </div>
    <div v-if="isLoading" class="bot-message loading-dots">
      <div class="dot"></div>
      <div class="dot"></div>
      <div class="dot"></div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    messages: {
      type: Array,
      required: true,
    },
    isLoading: {
      type: Boolean,
      required: true,
    },
  },
  methods: {
    getMessageClass(message) {
      return message.sender === 'user' ? 'user-message' : 'bot-message';
    },
  },
  updated() {
    this.$nextTick(() => {
      const { messageList } = this.$refs;
      messageList.scrollTop = messageList.scrollHeight;
    });
  },
};
</script>

<style scoped>
.message-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
  flex-grow: 1;
  max-height: calc(100vh - 120px);
  overflow-y: auto;
  padding: 10px;
  background-color: #ffffff;
}

.user-message,
.bot-message {
  padding: 10px;
  border-radius: 10px;
  max-width: 75%;
  word-wrap: break-word;
}

.user-message {
  align-self: flex-end;
  text-align: right;
  background-color: #d1f7d1;
}

.bot-message {
  align-self: flex-start;
  text-align: left;
  background-color: #f1f1f1;
}

.loading-dots {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 5px;
}

.dot {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.3); /* 초기 상태 색상 */
  animation: bounce 1.2s infinite ease-in-out;
}

.dot:nth-child(1) {
  animation-delay: 0s;
}

.dot:nth-child(2) {
  animation-delay: 0.2s;
}

.dot:nth-child(3) {
  animation-delay: 0.4s;
}

@keyframes bounce {
  0%, 80%, 100% {
    transform: scale(0.8);
    background-color: rgba(0, 0, 0, 0.3);
  }
  40% {
    transform: scale(1.0);
    background-color: rgba(0, 0, 0, 1);
  }
}
</style>
