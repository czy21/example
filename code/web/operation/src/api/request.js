import axios from 'axios'
import {Message} from 'element-ui'
import qs from 'qs'
import {getToken, removeToken} from '@/utils/auth';
import Router from '@/router';
import store from '@/store'

//配置API接口地址
const root = 'http://47.106.191.56:8089/api'

const service = axios.create({
  baseURL: root,
  timeout: 5000, //请求超时
  withCredentials: true
});
service.interceptors.request.use(config => {
    config.data = qs.stringify(config.data);
    config.headers['Content-Type'] = 'application/x-www-form-urlencoded';
    getToken() && (config.headers['Authorization'] = "Bearer " + getToken().value)
    return config;
  },
  error => {
    console.log('req' + error);
    Promise.reject(error);
  });

service.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    console.log('res' + error);
    Promise.reject(error)
  });

function apiAxios(method, url, params) {
  return new Promise((resolve, reject) => {
    service({
      method: method,
      url: url,
      data: method === 'POST' || method === 'PUT' ? params : null,
      params: method === 'GET' || method === 'DELETE' ? params : null
    }).then(res => {
      switch (res.data.result) {
        case 401:
          Message({
            message: "您没有此操作权限",
            type: 'warning',
          })
          break;
        case 402:
          Message({
            message: "登录信息过期,请重新登录",
            type: 'error',
          });
          setTimeout(() => {
            Router.push('/Login')
          }, 1000);
          break;
        default:
          res.data.pocket && store.commit('SET_POCKET_DATA', res.data.pocket)
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
  get: function (url, params) {
    return apiAxios('GET', url, params)
  },
  post: function (url, params) {
    return apiAxios('POST', url, params)
  },
  put: function (url, params) {
    return apiAxios('PUT', url, params)
  },
  delete: function (url, params) {
    return apiAxios('DELETE', url, params)
  }
}
