export default {
  key: 'helper',
  build(stub, deco = {}) {
    const helper = stub.ref.merge({}, deco.overrideObject || {})
    Object.defineProperty(stub.ref.vue.prototype, '$helper', {
      get: function () {
        return stub.ref.jsUtil.ext.buildBoundProxy(helper, this)
      }
    })
    return helper
  }
}
