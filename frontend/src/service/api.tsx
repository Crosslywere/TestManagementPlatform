import axios, { type InternalAxiosRequestConfig } from "axios";
import type { Classroom } from "../component/Classroom";

const api = axios.create({ baseURL: "http://localhost:8080/api" });

api.interceptors.request.use(
  (config): InternalAxiosRequestConfig => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error): Promise<never> => Promise.reject(error)
);

api.interceptors.response.use(
  (response) => response.data,
  (error): Promise<never> => {
    if (error.response?.status === 401) {
      localStorage.removeItem("token");
      window.location.href = "/login";
    }
    return Promise.reject(error);
  }
);

export const classroomService = {
  createClassroom: (name: string, domains: string): Promise<Classroom> => {
    return api.post("/test/classrooms/create", { name, domains });
  },
  getClassroom: (id: string): Promise<Classroom> =>
    api.get(`/classrooms/${id}`),
  getClassrooms: (): Promise<Classroom[]> => api.get("/test/classrooms"),
  deleteClassroom: (id: string): Promise<void> =>
    api.delete(`/test/delete/${id}`),
};
