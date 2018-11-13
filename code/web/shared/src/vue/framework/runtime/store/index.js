import data from './data'


export default {
  key: 'store',
  build(stub, deco = {}) {
    return new stub.ref.vuex.Store({
      modules: Object.assign({}, {data}, deco.modules),
      getters: deco.getters
    })
  }
}
console.log()
