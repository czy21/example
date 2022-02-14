const resolve = dir => require('path').join(__dirname, dir);

alias = {
    "@c": "src/component",
    "@v": "src/view",
}

module.exports = {
    resolve: {
        alias: Object.keys(alias).reduce((p, c) => ({...p, [c]: resolve(alias[c])}), {})
    }
}