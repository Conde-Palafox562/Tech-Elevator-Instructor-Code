import axios from 'axios'

export default {
    createTruck(truck){
        return axios.post("/trucks", truck);
    }
}