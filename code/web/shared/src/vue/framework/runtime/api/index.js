import axios from 'axios'
import qs from 'qs'

export default {
  key: 'api',
  build(stub, deco = {}) {

    //配置API接口地址
    const root = 'http://localhost:8075'

    const service = axios.create({
      baseURL: root,
      timeout: 5000,
    });
    service.interceptors.request.use(config => {
        config.data = qs.stringify(config.data);
        config.headers['Content-Type'] = 'application/x-www-form-urlencoded';
        stub.ref.jsUtil.auth.getToken() && (config.headers['Authorization'] = stub.ref.jsUtil.auth.getToken().value)
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
          res.data.data.hasOwnProperty("ErrorCode") ? stub.store.commit("SUBMIT_ERROR", res.data.data.Message) : resolve(res.data);
        }, error => {
          reject(error);
        }).catch(error => {
          reject(error)
        })
      })
    }

    const apiStub = {
      get: (url, params) => {
        return apiAxios('GET', url, params)
      },
      post: (url, params) => {
        return apiAxios('POST', url, params)
      },
      put: (url, params) => {
        return apiAxios('PUT', url, params)
      },
      delete: (url, params) => {
        return apiAxios('DELETE', url, params)
      }
    }
    stub.ref.vue.prototype.$api = apiStub
    return apiStub
  }
}


