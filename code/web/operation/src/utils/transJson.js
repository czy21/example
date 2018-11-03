/**
 * 转换数组为树形列表(无children空对象)
 * @params list     代转化数组列表
 * @params idStr id
 * @params pIdStr parentId
 * @params chindrenStr children
 */
export default function transJson(list, idStr, pIdStr, chindrenStr) {
  const temp = [], hash = {}, id = idStr, pId = pIdStr, children = chindrenStr;
  let i = 0, j = 0;
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
  return temp;
}
