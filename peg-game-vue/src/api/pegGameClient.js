import axios from "axios";

const apiClient = axios.create({
    baseURL: window.API_URL,
    headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
    }
});

export default apiClient;