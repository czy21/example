const resolve = dir => require('path').join(__dirname, dir);

alias = {
    "@": "src",
    "@c": "src/components",
    "@v": "src/views",
}

module.exports = {
    resolve: {
        alias: Object.keys(alias).reduce((p, c) => ({...p, [c]: resolve(alias[c])}), {})
    }
}