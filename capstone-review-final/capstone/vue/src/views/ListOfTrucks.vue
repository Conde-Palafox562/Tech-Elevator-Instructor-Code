<template>
  <div>
      <h1>List Of Trucks</h1>
      <h2 v-for="truck in trucks" v-bind:key="truck.truckId">
          <router-link v-bind:to="{name:'truck-details', params: {id: truck.truckId}}" >
          {{truck.truckName}} ( {{truck.phone}} )
          </router-link>
      </h2>
  </div>
</template>

<script>
import TruckService from '../services/TruckService.js'
export default {
    data(){
        return{
            trucks: []
        }
    },
    created(){
        TruckService.getTrucks().then(
            (response) => {
                    this.trucks = response.data;
            }
        )
    }
}
</script>

<style>

</style>