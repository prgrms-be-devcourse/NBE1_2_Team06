<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HealthCheck/>
  </div>
</template>

<script>
// @ is an alias to /src
import HealthCheck from '@/components/HealthCheck.vue';

export default {
  name: 'HomeView',
  components: {
    HealthCheck,
  },
};
</script>
