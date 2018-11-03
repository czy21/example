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
