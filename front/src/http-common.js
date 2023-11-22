import axios from "axios"

export default axios.create({
    baseURL: "http://localhost:8080",
    // baseURL: "http://165.132.221.100:8080",
    header:{
        "Content-type":"application/json",
    }
});