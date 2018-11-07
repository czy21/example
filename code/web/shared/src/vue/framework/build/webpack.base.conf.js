'use strict'
const path = require('path')
const _ = require('lodash')
const merge = require('webpack-merge')


module.exports = function (config) {
  const utils = require('./utils')(config)
  const vueLoaderConfig = require('./vue-loader.conf')(config)

  config.settings.modulesRequiresBabel.push('webpack-dev-server/client')
  require('babel-register')({
    ignore: function (filename) {
      return !_.some(config.settings.modulesRequiresBabel, v => {
        const ret = filename.endsWith('/node_modules/' + v) || filename.indexOf('/node_modules/' + v + '/') >= 0
        if (ret) {
          console.log(`processing babel in node_modules: ${filename}, under: ${v}`)
        }
        return ret
      })
    }
  })

  function resolve(dir) {
    return path.join(config.settings.projectRoot, dir)
  }

  function resolves(dir) {
    return _.map(config.settings.rootPaths, p => path.join(p, dir))
  }

  const modulePaths = [..._.map(_.union([config.settings.projectRoot]), v => path.resolve(v, 'node_modules')), 'node_modules']
  module.paths.unshift(...modulePaths)

  let result = {
    context: config.settings.projectRoot,
    entry: {
      app: [resolve('src/main.js')]
    },
    output: {
      path: config.build.assetsRoot,
      filename: '[name].js',
      publicPath: process.env.NODE_ENV === 'production'
        ? config.build.assetsPublicPath
        : config.dev.assetsPublicPath
    },
    resolve: {
      extensions: ['.js', '.vue', '.json'],
      alias: {
        'vue$': 'vue/dist/vue.esm.js',
        '@': resolve('src'),
      },
      modules: modulePaths
    },
    module: {
      rules: [
        {
          test: /\.vue$/,
          loader: 'vue-loader',
          options: vueLoaderConfig
        },
        {
          test: /\.js$/,
          include: [...resolves('src'), ...resolves('test'), ...config.settings.extraSourceRoots],
          loader: 'babel-loader'
        },
        {
          test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
          loader: 'url-loader',
          options: {
            limit: 10000,
            name: utils.assetsPath('img/[name].[hash:7].[ext]')
          }
        },
        {
          test: /\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
          loader: 'url-loader',
          options: {
            limit: 10000,
            name: utils.assetsPath('media/[name].[hash:7].[ext]')
          }
        },
        {
          test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
          loader: 'url-loader',
          options: {
            limit: 10000,
            name: utils.assetsPath('fonts/[name].[hash:7].[ext]')
          }
        }
      ]
    },
    node: {
      // prevent webpack from injecting useless setImmediate polyfill because Vue
      // source contains it (although only uses it if it's native).
      setImmediate: false,
      // prevent webpack from injecting mocks to Node native modules
      // that does not make sense for the client
      dgram: 'empty',
      fs: 'empty',
      net: 'empty',
      tls: 'empty',
      child_process: 'empty'
    }
  }

  result = merge(result, config.webpack)
  if (_.isFunction(config.decorate)) {
    result = config.decorate(result)
  }

  return result
}


