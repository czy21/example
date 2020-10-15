import {stub} from 'fr'

const user = {
  state: {
    // 前端显示用户实体
    userInfo: stub.ref.jsUtil.auth.getToken() ? stub.ref.jsUtil.auth.getToken().user : {},
    permissions: stub.ref.jsUtil.auth.getToken() ? stub.ref.jsUtil.auth.getToken().permissions : []
  },
  mutations: {
    // 设置当前用户信息
    SET_USER_INFO: (state, data) => {
      state.userInfo = data;
    },
    REMOVE_TOKEN: () => {
      stub.ref.jsUtil.auth.removeToken();
      stub.router.push("/login")
    }
  },
  actions: {
    CheckLogin({commit}, data) {
      stub.router.push("/home")
      // stub.api.post("user/login", data).then(
      //   res => {
      //     // stub.ref.jsUtil.auth.setToken(res.data.token);
      //     stub.router.push("/home")
      //     // window.location.reload()
      //   }
      // );
    },
  }
}
export default user
