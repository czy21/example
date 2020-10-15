import _ from 'lodash'

export function transDocsData(paths) {
  let list = []
  if (_.isObject(paths)) {
    for (var p in paths) {
      var temp = {url: p}
      var tempMethod = paths[p][Object.keys(paths[p])];
      temp.summary = tempMethod.summary
      temp.tag = tempMethod.tags[0]
      list.push(temp);
    }
  }
  return list;
}
