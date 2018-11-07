'use strict'

module.exports = function (config) {
  const utils = require('./utils')(config)
  const isProduction = process.env.NODE_ENV === 'production'
  const sourceMapEnabled = isProduction
    ? config.build.productionSourceMap
    : config.dev.cssSourceMap

  return {
    loaders: utils.cssLoaders({
      sourceMap: sourceMapEnabled,
      extract: isProduction,
      usePostCSS: true
    }),
    cssSourceMap: sourceMapEnabled,
    cacheBusting: config.dev.cacheBusting,
    transformToRequire: {
      video: ['src', 'poster'],
      source: 'src',
      img: 'src',
      image: 'xlink:href'
    }
  }
}
