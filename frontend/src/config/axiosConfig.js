import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080', // Schimbă cu URL-ul de bază al backend-ului tău
});

axiosInstance.interceptors.request.use(
    config => {
        if (config.url.startsWith('http://localhost:8080/api')) {
            const token = localStorage.getItem('token');
            if (token) {
                config.headers['Authorization'] = `Bearer ${token}`;
            }
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

export default axiosInstance;
