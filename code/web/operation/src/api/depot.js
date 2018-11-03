import request from './request';


export default {
  // 获取仓库列表
  getPageDepots: (pageIndex, pageSize) => {
    return request.get('depot/getPageDepots',{pageIndex: pageIndex, pageSize: pageSize});
  },

// 添加仓库
  create: (data) => {
    return request.post('depot/create', data);
  },

// 修改角色
  edit: (data) => {
    return request.put('depot/edit', data);
  },
// 删除仓库
  del: (id) => {
    return request.delete('depot/delete', {id: id});
  }

}


