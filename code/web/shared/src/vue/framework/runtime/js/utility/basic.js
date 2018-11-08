import _ from 'lodash'
import * as logger from './logger'

export function processComponents(components = [], stub = {}, decorators = {}, target = stub) {
  _(components)
    .forEach(c => {
      logger.log(logger.LOG_LEVEL_DEBUG, `Starting parsing component "${c.key}"...`)
      let deco = c.key == null ? undefined : decorators[c.key]
      if (deco != null && _.isFunction(deco._init)) {
        deco._init(stub, deco, decorators, target)
      }
      let v = _.isFunction(c.build) ? c.build(stub, deco, decorators, target) : undefined
      if (v !== undefined && c.key != null) {
        target[c.key] = v
      }
      logger.log(logger.LOG_LEVEL_DEBUG, `Completed parsing component "${c.key}".`)
    })
  return target
}

export function usePlugins(plugins, Vue) {
  _.forEach(plugins, p => (Vue || this).use(p))
}

