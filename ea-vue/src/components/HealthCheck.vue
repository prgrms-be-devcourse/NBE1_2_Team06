<script setup>
import { ref } from 'vue';
import { useHealthCheck } from '@/composables/api';

const data = ref('');
const healthCheck = async () => {
  data.value = await useHealthCheck();
};
</script>

<template>
  <button @click="healthCheck">Health Check Server</button>
  <div>{{ data }}</div>
</template>

<style scoped>

</style>
