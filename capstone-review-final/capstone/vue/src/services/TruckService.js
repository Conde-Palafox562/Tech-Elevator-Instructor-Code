import axios from 'axios'

export default {
    createTruck(truck){
        return axios.post("/trucks", truck);
    },
    getTruck(id){
        return axios.get("/trucks/"+id);
    },
    getTrucks(){
        return axios.get("/trucks")
    }
}