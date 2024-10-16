<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HealthCheck/>
    <button class="floating-chat-button" @click="navigateToChat">
      <i class="fas fa-first-aid"></i>
    </button>
  </div>
</template>

<script>
// @ is an alias to /src
import HealthCheck from '@/components/HealthCheck.vue';
import { useRouter } from "vue-router";

export default {
  name: 'HomeView',
  components: {
    HealthCheck,
  },
  setup() {
    const router = useRouter();

    const navigateToChat = () => {
      router.push('/chat');
    }

    return {
      navigateToChat,
    }
  }
};
</script>

<style scoped>
.home {
  position: relative;
}

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
</style>
