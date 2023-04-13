import axios from 'axios'

export default {
    getEvent(id){
        const url = "/events/" + id;
        return axios.get(url);
    },
    getEvents(){
        const url = "/events";
        return axios.get(url);
    },
    createEvent(event){
        const url = "/events";
        return axios.put(url, event);
    }
}