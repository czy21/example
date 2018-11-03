import api from "@/api/login"
import {getToken, setToken, removeToken} from "@/utils/auth"
import {Message} from 'element-ui'
import Router from '@/router'

const user = {
  state: {
    // 前端显示用户实体
    userInfo: getToken() ? getToken().user : {},
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
      api.checkLogin(data.loginName, data.password).then(
        res => {
          if (res.data.message.indexOf("user is not exist") !== -1) {
            Message({
              message: "用户不存在",
              type: 'warning',
              duration: 2 * 1000
            })
          } else if (res.data.message.indexOf("user password error") !== -1) {
            Message({
              message: "用户密码错误",
              type: 'warning',
              duration: 2 * 1000
            })
          } else if (res.data.message.indexOf("access service error") !== -1) {
            Message({
              message: "后台访问错误",
              type: 'warning',
              duration: 2 * 1000
            })
          } else if (res.data.message.indexOf("user can login") !== -1) {
            commit("SET_USER_INFO", res.data.token.user);
            setToken(res.data.token);
            Router.push("/Home");
          }
        }
      );
    },
  }
}
export default user
