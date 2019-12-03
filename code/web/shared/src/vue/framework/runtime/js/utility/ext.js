import _ from 'lodash'
import * as linq from "linq";

export function buildBoundProxy(target, thisArg) {
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

/**
 * Checks if `value` is classified as an `Array` object.
 *
 * example1 = {
 *          page: {
 *            pageIndex: 1,
 *            pageSize: 10
 *          },
 *          filter: [
 *            {
 *              loginName: "wobuzhidao"
 *            }
 *          ],
 *          arr:["ni","wo"],
 *          num:[1,2.3,567]
 *        };
 *
 *  // => {page:{pageIndex:1,pageSize:10},filter:[{loginName:"wobuzhidao"}],arr:["ni","wo"],num:[1,2.3,567]}
 *
 * example2= "who"
 *
 * // => "who"
 *
 */

export function recursionObject(obj) {
  if (_.isArray(obj)) {
    return "[" + linq.from(obj).select(s => this.recursionObject(s)).toArray().join() + "]"
  } else if (_.isString(obj)) {
    return "\"" + obj + "\""
  } else if (_.isNumber(obj) || _.isBoolean(obj)) {
    return obj
  }
  return "{" + linq.from(Object.entries(obj)).select(([k, v]) => {
    if (_.isArray(v)) {
      return k + ":" + "[" + linq.from(v).select(s => this.recursionObject(s)).toArray().join() + "]"
    } else if (_.isString(v)) {
      return k + ":" + "\"" + v + "\""
    } else if (_.isObject(v)) {
      return k + ":" + this.recursionObject(v)
    }
    return k + ":" + v
  }).toArray().join() + "}";

}
