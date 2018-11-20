const path = require('path')

const projectRoot = path.resolve(__dirname, '..')
const frameworkRoot = path.resolve(projectRoot, '../shared/src/vue/framework')
module.exports = {
  settings: {
    projectRoot: projectRoot,
    frameworkBuildRoot: path.resolve(frameworkRoot, 'build')
  },
  override: {
    dev: {
      proxyTable: {
        '/api': {
          target: 'http://localhost:8075',
          changeOrigin: true,
          pathRewrite: {
            '^/api': '/'
          }
        }
      },
      url: 'http://localhost:8080'
    },

  },
  build: {},
}
