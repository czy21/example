export default {
  key: 'store',
  build(stub, deco = {}) {
    return new stub.ref.vuex.Store({
      modules: deco.modules,
      getters: deco.getters
    })
  }
}
