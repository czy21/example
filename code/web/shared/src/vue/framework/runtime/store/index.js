export default {
  key: 'store',
  order: -600,
  build(stub, deco = {}) {
    return new stub.ref.vuex.Store({
      modules: deco.modules,
      getters: deco.getters
    })
  }
}
