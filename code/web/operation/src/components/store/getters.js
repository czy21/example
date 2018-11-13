const getters = {
  // 主界面
  sidebar: state => state.app.sidebar,
  isRouterAlive: state => state.app.isRouterAlive,
  userInfo: state => state.user.userInfo,
}

export default getters
