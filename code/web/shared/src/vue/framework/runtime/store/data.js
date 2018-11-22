import {stub} from '../../runtime'

export default {
  state: {
    pocket: {}
  },
  mutations: {
    SET_POCKET: (state, data) => {
      if (data.hasOwnProperty("menus")) {
        state.pocket.menuTree = stub.ref.jsUtil.forTree.transNoChild(data.menus, "value", "parentId", "children")
      }
      state.pocket = Object.assign({}, state.pocket, data)
    },
  }
}

