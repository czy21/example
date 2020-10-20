import axios, {Method} from 'axios'
import qs from 'qs'

//配置API接口地址
const root = 'api'

const service = axios.create({
    baseURL: root,
    timeout: 5000, //请求超时
    withCredentials: true
});
service.interceptors.request.use(config => {
        config.data = qs.stringify(config.data);
        config.headers['Content-Type'] = 'application/x-www-form-urlencoded';
        return config;
    },
    error => {
        console.log('request' + error);
        Promise.reject(error);
    });

service.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        console.log('response' + error);
        Promise.reject(error)
    });

function apiAxios(method: Method, url: string, params: any): Promise<any> {
    return new Promise((resolve, reject) => {
        service({
            method,
            url: url,
            data: method === 'POST' || method === 'PUT' ? params : null,
            params: method === 'GET' || method === 'DELETE' ? params : null
        }).then(res => {
            switch (res.data.result) {
                default:
                    resolve(res.data);
                    break;
            }
        }, error => {
            reject(error);
        }).catch(error => {
            reject(error)
        })
    })
}

export default {
    get: function (url: string, params: any) {
        return apiAxios('GET', url, params)
    },
    post: function (url: string, params: any) {
        return apiAxios('POST', url, params)
    },
    put: function (url: string, params: any) {
        return apiAxios('PUT', url, params)
    },
    delete: function (url: string, params: any) {
        return apiAxios('DELETE', url, params)
    }
}


export interface API {
    get(url: string, param?: any): Promise<any>

    post(url: string, param?: any): Promise<any>

    put(url: string, param?: any): Promise<any>

    delete(url: string, param?: any): Promise<any>
}