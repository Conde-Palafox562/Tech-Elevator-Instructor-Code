<template>
  <div>
      <h1>{{truck.truckName}}</h1>
      <h2>{{truck.phone}}</h2>
      <h3>{{truck.restaurantLink}}</h3>
      <p>Accepted Payment: {{truck.acceptedPayment}}</p>
      <p>Current Location: {{truck.currentLocation}}</p>
  </div>
</template>

<script>
import TruckService from '../services/TruckService.js'
export default {
    data(){
        return{
            truck: {}
        }
    },
    created(){
        TruckService.getTruck(this.$route.params.id).then(
            (response) => {
                this.truck = response.data;
            }
        )
    }
}
</script>

<style>

</style>