const cracoLessPlugin = require('craco-less');
const cracoAliasPlugin = require("craco-alias");
const webpackConfigPlugin = require("./webpack.config")

module.exports = {
    eslint: {
        enable: false
    },
    plugins: [
        {
            plugin: cracoLessPlugin,
            options: {
                lessLoaderOptions: {
                    lessOptions: {
                        javascriptEnabled: true,
                    },
                },
            },
        },
        {
            plugin: cracoAliasPlugin,
            options: {
                source: "tsconfig",
                baseUrl: ".",
                tsConfigPath: "./tsconfig.extend.json",
            }
        },
        {plugin: webpackConfigPlugin, options: {preText: "Will log the webpack config:"}}
    ],
};