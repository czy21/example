
import _ from 'lodash'

export function buildBoundProxy (target, thisArg) {
  if (_.isFunction(target)) {
    return new Proxy(target, {
      apply: function (target, ctx, args) {
        return Reflect.apply(target, thisArg, args)
      }
    })
  }
  if (_.isObject(target)) {
    return new Proxy(target, {
      get: function (target, key) {
        return buildBoundProxy(target[key], thisArg)
      }
    })
  }
  return target
}

