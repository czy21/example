import * as ref from './ref'
import router from './router'
import store from './store'
import launch from './launch'
import helper from './helper'

let stub = {ref}

stub.ref.jsUtil.basic.usePlugins([stub.ref.vuex, stub.ref.vueRouter], stub.ref.vue)

const register = ({decorators = {}, components = []} = {}) => {
  return ref.jsUtil.basic.processComponents(components, stub, decorators)
}

const prepare = ({decorators = {}} = {}) => {
  stub = register({
    decorators,
    components: [
      store,
      helper,
    ]
  })
  return stub
}

const start = ({decorators = {}} = {}) => {
  stub = register({
    decorators,
    components: [
      router,
      launch,
    ]
  })
  return stub
}

export {stub, start, register, prepare}
