import list from './list'

export default {
  key: 'mixins',
  build(stub, deco = {}) {
    Object.defineProperties(stub.ref.vue.prototype, {
      '$hasPermission': {
        value: (item) => {
          if (!stub.ref.lodash.isArray(item)) {
            item = [item]
          }
          return stub.ref.lodash.indexOf(stub.store.getters.permissions, ...item) > -1
        },
      }
    })
    stub.ref.vue.mixin({
      computed: {
        $pocket() {
          return stub.store.state.data.pocket
        }
      }
    })
    return {list}
  }
}

