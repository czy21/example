/**
 * 根据子节点寻找所有父节点
 * @params list 树形集合
 * @params id 子节点Id
 */
export default function findParent(list, id) {
  let result = []
  for (let i in list) {
    if (list[i].value === id) {
      result.push(list[i].parentId)
      result.push(list[i].value)
      return result;
    }
    else if (list[i].children && list[i].children.length > 0) {
      let res = findParent(list[i].children, id);
      return res.concat(list[i].parentId)
    }
  }
}
