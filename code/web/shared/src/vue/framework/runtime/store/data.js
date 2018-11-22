import {stub} from '../../runtime'

export default {
  state: {
    pocket: {
      menuTree: []
    }
  },
  mutations: {
    SET_POCKET: (state, data) => {
      state.pocket = Object.assign({}, state.pocket, data)
      if (state.pocket.hasOwnProperty("menus")) {
        state.pocket.menuTree = stub.ref.jsUtil.forTree.transNoChild(data.menus, "value", "parentId", "children")
      }
    },
  }
}

