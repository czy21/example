import axios from 'axios'

export default {
  key: 'api',
  build(stub, deco = {}) {

    //配置API接口地址
    const root = '/api';

    const service = axios.create({
      baseURL: root,
      timeout: 5000,
    });
    service.interceptors.request.use(request => {
        // request.data = qs.stringify(request.data, {arrayFormat: 'brackets'});
        // request.headers['Content-Type'] = 'application/x-www-form-urlencoded';
        stub.ref.jsUtil.auth.getToken() && (request.headers['Authorization'] = stub.ref.jsUtil.auth.getToken().value)
        return request;
      },
      error => Promise.reject(error));

    service.interceptors.response.use(
      response => response,
      error => Promise.reject(error));

    function apiAxios(method, url, params) {
      return new Promise((resolve, reject) => {
        service({
          method: method,
          url: url,
          data: method === 'POST' || method === 'PUT' ? params : null,
          params: method === 'GET' || method === 'DELETE' ? params : null
        }).then(res => {
          res.data.pocket && stub.store.commit('SET_POCKET', res.data.pocket);
          return resolve(res.data)
        }, error => {
          if (error && error.response) {
            stub.store.commit("SUBMIT_ERROR", error.response.data.status + ": " + error.response.data.message)
          }
          return reject(error)
        }).catch(error => reject(error))
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
      },
      graphql: {
        get: (params) => {
          return apiAxios('GET', 'graphql', params)
        },
        post: (params) => {
          return apiAxios('POST', 'graphql', params)
        }
      }
    };
    stub.ref.vue.prototype.$api = apiStub;
    return apiStub
  }
}


