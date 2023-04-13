<template>
  <div>
      <form v-on:submit.prevent="saveMenuItem()">
          <div>
              <label> Name </label>
              <input type='checkbox' v-model="menuItem.menuItemName" />
          </div>
          <div>
              <label> Description </label>
              <textarea v-model="menuItem.description" />
          </div>
          <div>
              <label> Price </label>
              <input type='number' v-model="menuItem.price" />
          </div>
          <div>
              <label> Active </label>
              <input type='checkbox' v-model="menuItem.active" />
          </div>
          <input type="submit" value="Create!" />
      </form>
  </div>
</template>

<script>
import MenuService from '../services/MenuService.js'

export default {
    data(){
        return {
            menuItem: {
                menuItemId: 0,
                subMenuId: 0,
                active: true,
                price: 0,
                menuItemName: "",
                description: ""
            }
        }
    },
    methods: {
        saveMenuItem(){
            MenuService.createMenuItem(this.menuItem).then(
                (response) => {
                    alert("Success! New id is " + response.data.menuItemId);
                }
            )
        }
    }
}
</script>

<style>

</style>