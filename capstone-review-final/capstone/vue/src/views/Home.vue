<template>
  <div class="home">
    <h1>Home</h1>
    <router-link v-bind:to="{name: 'trucks'}"> See All Trucks</router-link>
    <br/>
    <router-link v-bind:to="{name: 'new-truck'}">
      <img src="../img/NewTruck.png" />
    </router-link>
  </div>
</template>

<script>


export default {
  name: "home",
  components: {
   
  }
};
</script>

<style scoped>
img {
  width: 120px;
}
</style>
