import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import custom from '@v/custom'
import euiHelpers from './eui'

export default {
  key: 'ui',
  build(stub) {
    stub.helper.eui = euiHelpers(stub)
    stub.ref.jsUtil.basic.usePlugins([{plugin: ElementUI, params: {size: 'mini'}}, custom], stub.ref.vue)
    return {eui: ElementUI, custom: custom.components}
  }
}
