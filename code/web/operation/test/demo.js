const _ = require('lodash')

var users = [
  { 'user': 'barney' },
  { 'user': 'fred' }
];
var temp=_.map(users,'user')
console.log(temp)

