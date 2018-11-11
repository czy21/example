const pocket = {
  state: {
    pocketData: {
      roles: [],
      menus: [],
      departments: [],
      categories: [],
    }
  },
  mutations: {
    SET_POCKET_DATA: (state, data) => {
      state.pocketData = Object.assign({}, state.pocketData, data)
    },
  }
}

export default pocket
