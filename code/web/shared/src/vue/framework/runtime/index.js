import * as ref from './ref'
import router from './router'
import store from './store'
import launch from './launch'
import helper from './helper'
import api from './api'

let stub = {ref}

stub.ref.jsUtil.basic.usePlugins([stub.ref.vuex, stub.ref.vueRouter], stub.ref.vue)

const register = ({decorators = {}, components = []} = {}) => {
  return ref.jsUtil.basic.processComponents(components, stub, decorators)
}

const prepare = ({decorators = {}, extraComponents = []} = {}) => {
  stub = register({
    decorators,
    components: [
      store,
      api,
      helper,
      ...extraComponents
    ]
  })
  return stub
}

const start = ({decorators = {}} = {}) => {
  return register({decorators, components: [router, launch]})
}

export {stub, start, prepare}
console.log(stub)
