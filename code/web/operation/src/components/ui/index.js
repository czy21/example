import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import '@a/icon/iconfont.css';
import euiHelpers from './eui'

export default {
  key: 'ui',
  build(stub) {
    stub.helper.eui = euiHelpers(stub)
    stub.ref.jsUtil.basic.usePlugins([{plugin: ElementUI, params: {size: 'mini'}}], stub.ref.vue)
    return ElementUI
  }
}
