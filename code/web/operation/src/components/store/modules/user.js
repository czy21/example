import * as auth from '@fr/api/auth'
import {stub} from 'fr'

const user = {
  state: {
    // 前端显示用户实体
    userInfo: auth.getToken() ? auth.getToken().user : {},
  },
  mutations: {
    // 设置当前用户信息
    SET_USER_INFO: (state, data) => {
      state.userInfo = data;
    },
    REMOVE_TOKEN: () => {
      auth.removeToken();
      stub.router.push("/login")
    }
  },
  actions: {
    CheckLogin({commit}, data) {
      stub.api.post("user/login", data).then(
        res => {
          commit("SET_USER_INFO", res.data.token.user);
          auth.setToken(res.data.token);
          stub.router.push("/home")
        }
      );
    },
  }
}
export default user
