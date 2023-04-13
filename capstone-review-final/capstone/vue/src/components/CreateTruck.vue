<template>
  <div>
      <form v-on:submit.prevent="createTruck()">
          <div>
              <label> Active </label>
              <input type='checkbox' v-model="truck.active" />
          </div>
          <div>
              <label> Accepted Payment </label>
              <input type='text' v-model="truck.acceptedPayment" />
          </div>
          <div>
              <label> Restaurant Link </label>
              <input type='text' v-model="truck.restaurantLink" />
          </div>
          <div>
              <label> Phone </label>
              <input type='text' v-model="truck.phone" />
          </div>
          <div>
              <label> Current Location </label>
              <input type='text' v-model="truck.currentLocation" />
          </div>
          <input type="submit" value="Create!" />
      </form>
  </div>
</template>

<script>
import TruckService from '../services/TruckService.js'

export default {
    data(){
        return {
            truck: {
               active: false,
               acceptedPayment: "",
               restaurantLink: "",
               phone: "",
               currentLocation: "" 
            }
        }
    },
    methods: {
        createTruck(){
            TruckService.createTruck(this.truck).then(
                (response) => {
                    alert("Success! New id is " + response.data.truckId);
                }
            )
        }
    }
}
</script>

<style>

</style>