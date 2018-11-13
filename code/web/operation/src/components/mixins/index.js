import list from './list'

const _ = require('lodash')

export default {
  key: 'mixins',
  build(stub, deco = {}) {
    stub.ref.vue.mixin({
      computed: {
        $pocket() {
          return stub.store.state.data.pocket
        }
      },
      methods: {
        reload() {
          stub.store.commit("RELOAD_ROUTE", false)
          this.$nextTick(() => {
            stub.store.commit("RELOAD_ROUTE", true)
          })
        }
      }
    })
    return {list}
  }
}

