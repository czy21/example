let webpackConfig = require('./webpack.config')

module.exports = {
    configureWebpack: config => {
        Object.assign(config.resolve.alias, webpackConfig.resolve.alias)
    },
    outputDir: "build",
    devServer: {
        host: "localhost",
        port: 3000,
        proxy: {
            "/api": {
                target: 'http://localhost:8075',
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/api': '',
                }
            }
        },
    },
    publicPath: process.env.BASE_URL
};
