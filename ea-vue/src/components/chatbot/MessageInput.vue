<template>
  <div class="input-container">
    <textarea v-model="message"
              @keyup.enter="handleKeyUp"
              placeholder="질문을 입력하세요."
              class="message-input"
              aria-label="input-message"
    ></textarea>
    <button @click="sendMessage" class="send-button">
      <i class="fas fa-paper-plane"></i>
    </button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      message: '',
    };
  },
  methods: {
    handleKeyUp(event) {
      if (!event.shiftKey) {
        event.preventDefault();
        this.sendMessage();
      }
    },
    sendMessage() {
      if (this.message.trim()) {
        this.$emit('sendMessage', this.message);
        this.message = '';
      }
    },
  },
};
</script>

<style scoped>
.input-container {
  display: flex;
  align-items: center;
  padding: 10px;
  border-top: 1px solid #e0e0e0;
  background-color: #ffffff;
}

.message-input {
  flex: 1;
  padding: 10px;
  border: 1px solid #e0e0e0;
  border-radius: 10px;
  margin-right: 10px;
}

.send-button {
  padding: 10px 15px;
  border: none;
  border-radius: 10px;
  background-color: #007bff;
  color: #ffffff;
  cursor: pointer;
}

.send-button:hover {
  background-color: #0056b3;
}

@media (max-width: 600px) {
  .message-input {
    font-size: 14px;
  }
  .send-button {
    padding: 8px 12px;
  }
}
</style>
