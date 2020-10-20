let _ = require('lodash')

const callIfExists = function (fn: Function, resultForNonFunction?: any, ...args: any) {
    if (_.isFunction(fn)) {
        return fn.apply(args)
    }
    return resultForNonFunction
}

export default {
    callIfExists
}