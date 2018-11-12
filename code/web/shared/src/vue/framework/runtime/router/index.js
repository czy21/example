export default {
  key: 'router',
  build(stub, deco = {}) {
    return new stub.ref.vueRouter({
      mode: deco.mode || 'history',
      routes: deco.routes
    })
  }
}
