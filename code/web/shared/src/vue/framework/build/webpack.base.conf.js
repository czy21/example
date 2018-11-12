'use strict'
const path = require('path')
const _ = require('lodash')

module.exports = function (config) {
  const utils = require('./utils')(config)
  const vueLoaderConfig = require('./vue-loader.conf')(config)


  function resolve(dir) {
    return path.join(config.settings.projectRoot, dir)
  }

  const modulePaths = [..._.map(_.union([config.settings.sharedRoot], [config.settings.projectRoot]), v => path.resolve(v, 'node_modules')), 'node_modules']
  module.paths.unshift(...modulePaths)

  let result = {
    context: config.settings.projectRoot,
    entry: {
      app: resolve('src/main.js')
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
        '@a': resolve('src/assets'),
        '@c': resolve('src/components'),
        '@v': resolve('src/views'),
        '@init': resolve('src/components/init'),
        '@fr': path.resolve(config.settings.frameworkRoot, 'runtime'),
        'fr$': path.resolve(config.settings.frameworkRoot, 'runtime'),
        'fref$': path.resolve(config.settings.frameworkRoot, 'runtime/ref'),
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
          include: [resolve('src'), config.settings.frameworkRuntimeRoot, resolve('node_modules/webpack-dev-server/client')],
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

  return result
}


