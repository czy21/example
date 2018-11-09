import app from './view/app'

export default {
  key: 'launch',
  build(stub, deco = {}) {
    return new stub.ref.vue(Object.assign({
      el: '#app',
      router: stub.router,
      store: stub.store,
      template: '<app/>',
      components: {app}
    }, deco.rootVueComponent))
  }
}
