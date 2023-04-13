<template>
  <div>
      <form v-on:submit.prevent="saveMenu()">
          <div>
              <label> Name </label>
              <input type='text' v-model="submenu.subMenuName" />
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
            submenu: {
                subMenuId: 0,
                menuId: 0,
                subMenuName: ""
            }
        }
    },
    methods: {
        saveMenu(){
            MenuService.createSubMenu(this.submenu).then(
                (response) => {
                    alert("Success! New id is " + response.data.subMenuId);
                }
            )
        }
    }
}
</script>

<style>

</style>