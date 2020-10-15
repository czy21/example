
const LOG_LEVEL_ALL = 0
const LOG_LEVEL_VERBOSE = 100
const LOG_LEVEL_DEBUG = 300
const LOG_LEVEL_INFO = 500
const LOG_LEVEL_WARNING = 700
const LOG_LEVEL_ERROR = 900

let levelLimit = LOG_LEVEL_ALL

const log = (level, content, { category, moreArgs = [] } = {}) => {
  if (level >= levelLimit) {
    console.log(content, ...moreArgs)
  }
}

const setLevelLimit = limit => { levelLimit = limit }

export {
  LOG_LEVEL_ALL,
  LOG_LEVEL_VERBOSE,
  LOG_LEVEL_DEBUG,
  LOG_LEVEL_INFO,
  LOG_LEVEL_WARNING,
  LOG_LEVEL_ERROR,
  log,
  setLevelLimit
}
