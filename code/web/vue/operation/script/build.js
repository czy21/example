
const path = require('path')
const cfg = require('./cfg')
require(path.resolve(__dirname, cfg.settings.frameworkBuildRoot, 'build'))(cfg)
