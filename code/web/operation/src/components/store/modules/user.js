import {stub} from 'fr'

const user = {
  state: {
    // 前端显示用户实体
    userInfo: stub.ref.jsUtil.auth.getToken() ? stub.ref.jsUtil.auth.getToken().user : {},
  },
  mutations: {
    // 设置当前用户信息
    SET_USER_INFO: (state, data) => {
      state.userInfo = data;
    },
    REMOVE_TOKEN: () => {
      removeToken();
    }
  },
  actions: {
    CheckLogin({commit}, data) {
      stub.api.post("user/login", data).then(
        res => {
          commit("SET_USER_INFO", res.data.token.user);
          stub.ref.jsUtil.auth.setToken(res.data.token);
        }
      );
    },
  }
}
export default user
