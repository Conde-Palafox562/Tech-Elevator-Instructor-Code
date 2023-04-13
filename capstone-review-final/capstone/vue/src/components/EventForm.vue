<template>
  <div>
      <form v-on:submit.prevent="saveEvent()">
          <div>
              <label> Name </label>
              <input type='text' v-model="truckEvent.eventName" />
          </div>
          <div>
              <label> Start </label>
              <input type='datetime-local' v-model="truckEvent.eventStart" />
          </div>
          <div>
              <label> End </label>
              <input type='datetime-local' v-model="truckEvent.eventEnd" />
          </div>
          <input type="submit" value="Create!" />
      </form>
  </div>
</template>

<script>
import EventService from '../services/EventService.js'

export default {
    data(){
        return {
            truckEvent: {
                eventId: 0,
                eventName: "",
                eventDate: null,
                eventStart: null,
                eventEnd: null,
                truckId: 0,
                locationId: 0
            }
        }
    },
    methods: {
        saveEvent(){
            EventService.createEvent(this.truckEvent).then(
                (response) => {
                    alert("Success! New id is " + response.data.eventId);
                }
            )
        }
    }
}
</script>

<style>

</style>