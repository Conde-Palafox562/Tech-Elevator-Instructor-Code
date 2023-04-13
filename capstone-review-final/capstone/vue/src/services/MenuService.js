import axios from 'axios'

export default {
    getMenu(id){
        const url = "/menu/"+id;
        return axios.get(url);
    },
    createMenu(menu){
        const url="/menu";
        return axios.post(url, menu);
    },
    createSubMenu(submenu){
        const url = "/submenu";
        return axios.post(url, submenu);
    },
    createMenuItem(menuItem){
        const url = "/menuitem";
        return axios.post(url, menuItem);
    } 
}