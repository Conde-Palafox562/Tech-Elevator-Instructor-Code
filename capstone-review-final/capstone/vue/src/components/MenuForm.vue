<template>
  <div>
      <form v-on:submit.prevent="saveMenu()">
          <div>
              <label>Menu Name</label>
              <input type='text' v-model="menu.menuName" />
          </div>
          <div>
              <label>Hours Available</label>
              <input type='text' v-model="menu.hoursAvailable" />
          </div>
          <div>
              <label>Active</label>
              <input type='checkbox' v-model="menu.active" />
          </div>
          <input type="submit" value="Save" />
      </form>
  </div>
</template>

<script>
import MenuService from '../services/MenuService.js'

export default {
    data(){
        return {
            menu: {
                menuId: 0,
                truckId: 0,
                hoursAvailable: "",
                active: true,
                menuName: ""
            }
        }
    },
    methods: {
        saveMenu(){
            
            MenuService.createMenu(this.menu).then(
                (response) => {
                    alert("Success! New id is " + response.data.menuId);
                }
            )
        }
    }
}
</script>

<style>

</style>