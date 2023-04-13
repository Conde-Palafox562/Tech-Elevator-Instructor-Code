<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <create-truck />
  </div>
</template>

<script>
import CreateTruck from '../components/CreateTruck.vue';

export default {
  name: "home",
  components: {
    CreateTruck
  }
};
</script>
