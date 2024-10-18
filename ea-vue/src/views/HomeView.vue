<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HealthCheck/>
    <ChatbotView/>
  </div>
</template>

<script>
// @ is an alias to /src
import HealthCheck from '@/components/HealthCheck.vue';
import ChatbotView from '@/views/ChatbotView.vue';

export default {
  name: 'HomeView',
  components: {
    HealthCheck,
    ChatbotView,
  },
};
</script>

<style scoped>
.home {
  position: relative;
}
</style>
