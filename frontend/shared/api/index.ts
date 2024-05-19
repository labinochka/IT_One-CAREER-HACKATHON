import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:8080/api/v1",
  headers: {
    "Content-Type": "application/json",
  },
});

api.interceptors.request.use((config) => {
  const accessToken = localStorage.getItem('access-token');
  if (accessToken && config.headers)
    config.headers.Authorization = `Bearer ${accessToken}`;

  return config;
});
