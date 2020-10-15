import app from './view/app'
import {sync} from 'vuex-router-sync'

export default {
  key: 'launch',
  build(stub, deco = {}) {
    sync(stub.store, stub.router)
    return new stub.ref.vue(Object.assign({
      el: '#app',
      router: stub.router,
      store: stub.store,
      template: '<app/>',
      components: {app}
    }, deco.rootVueComponent))
  }
}
