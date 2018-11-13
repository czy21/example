export default {
  state: {
    pocket: {}
  },
  mutations: {
    SET_POCKET: (state, data) => {
      state.pocket = Object.assign({}, state.pocket, data)
    },
  }
}

