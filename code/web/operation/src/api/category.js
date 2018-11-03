import request from './request';

export default {
  // 获取商品类别列表
  getCategories: () => {
    return request.get('category/getCategories');
  },
  // 添加商品类别
  create: (data) => {
    return request.post('category/create', data);
  },
  // 修改商品类别
  edit: (data) => {
    return request.put('category/edit', data);
  },
// 删除商品类别
  del: (id) => {
    return request.delete('category/delete', {id: id});
  }
}

