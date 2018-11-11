import _ from 'lodash'
import * as logger from './logger'


export function callIfExists(fn, resultForNonFunction, ...args) {
  if (_.isFunction(fn)) {
    return fn.apply(args)
  }
  return resultForNonFunction
}


export function processComponents(components = [], stub = {}, decorators = {}, target = stub) {
  _(components)
    .forEach(c => {
      logger.log(logger.LOG_LEVEL_DEBUG, `Starting parsing component "${c.key}".`)
      target[c.key] = c.build(stub, decorators[c.key], target)
      logger.log(logger.LOG_LEVEL_DEBUG, `Completed parsing component "${c.key}".`)
    })
  return target
}

/**
 * Vue批量注入插件
 * @params 插件集合
 * @params vue实例
 */
export function usePlugins(plugins, Vue) {
  _.forEach(plugins, p => {
    p.hasOwnProperty("plugin") || p.hasOwnProperty("params") ? Vue.use(p.plugin, p.params) : Vue.use(p)
  })
}

