const getters = {
  // 主界面
  sidebar: state => state.app.sidebar,
  userInfo: state => state.user.userInfo,
  permissions: state => state.user.permissions,
}

export default getters
