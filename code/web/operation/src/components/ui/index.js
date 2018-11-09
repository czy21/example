import ElementUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';

export default {
  key: 'ui',
  build(stub, deco = {}) {
    const ui = stub.ref.jsUtil.basic.usePlugins([ElementUi], stub.ref.vue)
    Object.defineProperty(stub.ref.vue.prototype, '$ui', {
      get: function () {
        return ui
      }
    })
    return ui
  }
}
