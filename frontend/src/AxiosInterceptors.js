import axios from 'axios'
import {getToken} from "./TokenStorage";

// Add a request interceptor
axios.interceptors.request.use(
    config => {
        const token = getToken()
        if (token) {
            config.headers['Authorization'] = token;
        }
        config.headers['Content-Type'] = 'application/json';
        return config
    },
    error => {
        Promise.reject(error)
    }
)

export function errorHandler(error, navigate) {
    if (error.response.status === 401) {
        navigate("/login")
    } if (error.response.status === 403) {
        alert("User is not permitted to perform this action.")
    }
    return Promise.reject(error)
}