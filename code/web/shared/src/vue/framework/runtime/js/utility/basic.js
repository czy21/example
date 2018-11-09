import _ from 'lodash'
import * as logger from './logger'

export function processComponents(components = [], stub = {}, decorators = {}, target = stub) {
  _(components)
    .forEach(c => {
      logger.log(logger.LOG_LEVEL_DEBUG, `Starting parsing component "${c.key}".`)
      target[c.key] = c.build(stub, decorators[c.key], target)
      logger.log(logger.LOG_LEVEL_DEBUG, `Completed parsing component "${c.key}".`)
    })
  return target
}

export function usePlugins(plugins, Vue) {
  _.forEach(plugins, p => Vue.use(p))
}

