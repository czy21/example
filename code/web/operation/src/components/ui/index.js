import ElementUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import euiHelpers from './eui'

export default {
  key: 'ui',
  build(stub) {
    stub.helper.eui = euiHelpers(stub)
    stub.ref.jsUtil.basic.usePlugins([ElementUi], stub.ref.vue)
  }
}
