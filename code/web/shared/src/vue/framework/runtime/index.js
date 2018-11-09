import * as ref from './ref'
import router from './router'
import store from './store'
import launch from './launch'

let stub = {ref}

stub.ref.jsUtil.basic.usePlugins([stub.ref.vuex, stub.ref.vueRouter], stub.ref.vue)

const register = ({decorators = {}, components = []} = {}) => {
  stub = ref.jsUtil.basic.processComponents(components, stub, decorators)
  return stub
}

const start = ({decorators = {}} = {}) => {
  stub = register({
    decorators,
    components: [
      router,
      store,
      launch,
    ]
  })
  console.log(stub)
  return stub
}

export {stub, start,register}
