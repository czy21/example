
export default {
  state: {
    pocket: {
      menuTree:[]
    }
  },
  mutations: {
    SET_POCKET: (state, data) => {
      state.pocket = Object.assign({}, state.pocket, data)
    },
  }
}

