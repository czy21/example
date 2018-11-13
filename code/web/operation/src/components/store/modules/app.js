import {stub} from 'fr'

const app = {
  state: {
    sidebar: {
      // 是否收缩菜单状态
      opened: false,
      // 侧边栏用户树形菜单列表
      userMenuTree: []
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
    // 设置侧边栏树形菜单数据
    SET_USER_MENU_TREE: (state, data) => {
      state.sidebar.userMenuTree = data;
    },
    SUBMIT_ERROR: (state, err) => {
      stub.launch.$helper.eui.inform(err)
    }
  },
  actions: {
    // 触发设置收缩状态方法
    ToggleSideBar({commit}) {
      commit('TOGGLE_SIDEBAR')
    },
    // 获取用户树形菜单列表
    GetUserMenus({commit}, loginName) {
      stub.api.getUserMenus(loginName).then(res => {
        commit('SET_USER_MENU_TREE', stub.ref.jsUtil.forTree.transChild(res.data));
      })
    }
  }
}
export default app
