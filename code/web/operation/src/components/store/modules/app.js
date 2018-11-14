import {stub} from 'fr'

const app = {
  state: {
    sidebar: {
      // 是否收缩菜单状态
      opened: false,
      // 侧边栏用户树形菜单列表
      userMenuTree: stub.ref.jsUtil.auth.getToken() ? stub.ref.jsUtil.auth.getToken().menus : []
    },
    isRouterAlive: true
  },
  mutations: {
    RELOAD_ROUTE: (state, data) => {
      state.isRouterAlive = data
    },
    // 设置收缩状态
    TOGGLE_SIDEBAR: state => {
      state.sidebar.opened = !state.sidebar.opened;
    },
    SUBMIT_ERROR: (state, err) => {
      stub.launch.$helper.eui.inform(err)
    }
  },
  actions: {
    // 触发设置收缩状态方法
    ToggleSideBar({commit}) {
      commit('TOGGLE_SIDEBAR')
    }
  }
}
export default app
