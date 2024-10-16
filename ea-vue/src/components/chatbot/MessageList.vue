<template>
  <div class="message-list" ref="messageList">
    <div v-for="(message, index) in messages" :key="index" :class="getMessageClass(message)">
      <span v-html="message.text"></span>
    </div>
    <div v-if="isLoading" class="bot-message loading-spinner">
      <span class="spinner"></span>
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
      const messageList = this.$refs.messageList;
      messageList.scrollTop = messageList.scrollHeight;
    });
  }
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
  border: 1px solid #e0e0e0;
  border-radius: 10px;
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

.loading-spinner {
  display: flex;
  justify-content: center;
  align-items: center;
}

.spinner {
  border: 4px solid rgba(0, 0, 0, 0.1);
  border-left-color: #242424;
  border-radius: 50%;
  width: 15px;
  height: 15px;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
