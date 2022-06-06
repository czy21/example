import axios, {AxiosRequestConfig} from 'axios'

enum Method {
    GET = "GET",
    POST = "POST",
    PUT = "PUT",
    DELETE = "DELETE"
}

const service = axios.create({
    baseURL: '/api',
    timeout: 5000,
});
service.interceptors.request.use(
    request => request,
    error => Promise.reject(error)
);

service.interceptors.response.use(
    response => response,
    error => Promise.reject(error)
);

function apiAxios(method: Method, url: string, params: any, config?: AxiosRequestConfig) {
    return new Promise((resolve, reject) => {
        service({
            method: method,
            url: url,
            data: method === Method.POST || method === Method.PUT ? params : null,
            params: method === Method.GET || method === Method.DELETE ? params : null,
            ...config
        }).then((res) => {
            resolve(res)
        }, error => reject(error))
            .catch(error => reject(error))
    })
}

export default {
    get: (url: string, params?: any, config?: AxiosRequestConfig) => {
        return apiAxios(Method.GET, url, params, config)
    },
    post: (url: string, params?: any, config?: AxiosRequestConfig) => {
        return apiAxios(Method.POST, url, params, config)
    },
    put: (url: string, params?: any, config?: AxiosRequestConfig) => {
        return apiAxios(Method.PUT, url, params, config)
    },
    delete: (url: string, params?: any, config?: AxiosRequestConfig) => {
        return apiAxios(Method.DELETE, url, params, config)
    }
};

export interface API {
    get(url: string, param?: any, config?: AxiosRequestConfig): Promise<any>

    post(url: string, param?: any, config?: AxiosRequestConfig): Promise<any>

    put(url: string, param?: any, config?: AxiosRequestConfig): Promise<any>

    delete(url: string, param?: any, config?: AxiosRequestConfig): Promise<any>
}