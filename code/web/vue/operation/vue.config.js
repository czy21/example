let webpackConfig = require('./webpack.config')

let _ = require('lodash')

module.exports = {
    configureWebpack: config => {
        Object.assign(config.resolve.alias, webpackConfig.resolve.alias)
    }
};
