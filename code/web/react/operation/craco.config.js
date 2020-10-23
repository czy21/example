const cracoLessPlugin = require('craco-less');
const cracoAliasPlugin = require("craco-alias");

module.exports = {
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
        }
    ],
};