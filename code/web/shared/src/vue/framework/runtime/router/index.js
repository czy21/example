export default {
  key: 'router',
  build(stub, deco = {}) {
    return new stub.ref.vueRouter({
      mode: 'history',
      routes: deco.routes
    })
  }
}
