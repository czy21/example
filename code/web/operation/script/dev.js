
const path = require('path')
const cfg = require('./cfg')
module.exports = require(path.resolve(__dirname, cfg.settings.frameworkBuildRoot, 'webpack.dev.conf'))(cfg)
