const _ = require('lodash')
/**
 * 转换数组为树形列表(有children空对象)
 * @params list     代转化数组列表
 */
export default function transData(list) {
  let parentId = '00000000-0000-0000-0000-000000000000';
  let items = {};
  // 获取每个节点的直属子节点，*记住是直属，不是所有子节点
  for (let i = 0; i < list.length; i++) {
    let key = list[i].parentId;
    if (items[key]) {
      items[key].push(list[i]);
    } else {
      items[key] = [];
      items[key].push(list[i]);
    }
  }
  return formatTree(items, parentId);
}

/**
 * 利用递归格式化每个节点
 */
function formatTree(items, parentId) {
  let result = [];
  if (!items[parentId]) {
    return result;
  }
  for (let t of items[parentId]) {
    t.children = formatTree(items, t.id)
    result.push(t);
  }
  return result;
}

/**
 * 转换数组为树形列表(无children空对象)
 * @params list     代转化数组列表
 * @params idStr id
 * @params pIdStr parentId
 * @params chindrenStr children
 */
export function transNoChild(list, idStr, pIdStr, chindrenStr) {
  const temp = [], hash = {}, id = idStr, pId = pIdStr, children = chindrenStr;
  let i = 0, j = 0;
  if (!_.isEmpty(list)) {
    const len = list.length;
    for (; i < len; i++) {
      hash[list[i][id]] = list[i];
    }
    for (; j < len; j++) {
      const aVal = list[j], hashVP = hash[aVal[pId]];
      if (hashVP) {
        !hashVP[children] && (hashVP[children] = []);
        hashVP[children].push(aVal);
      } else {
        temp.push(aVal);
      }
    }
  }
  return temp;
}

/**
 * 根据子节点寻找所有父节点
 * @params list 树形集合
 * @params id 子节点Id
 */
export function findParents(list, id) {
  let result = []
  if (!_.isEmpty(list)) {
    for (let i in list) {
      if (list[i].value === id) {
        result.push(list[i].parentId)
        result.push(list[i].value)
        return result;
      }
      else if (list[i].children && list[i].children.length > 0) {
        let res = findParents(list[i].children, id);
        return res.concat(list[i].parentId)
      }
    }
  }
  return result
}
