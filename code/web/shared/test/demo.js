var merge = require('deepmerge')

var x = {foo: {bar: 3}}

var y = {foo: {baz: 4}}

var z = {bar: 'yay!'}

var temp = merge(x, {})

console.log(temp)
