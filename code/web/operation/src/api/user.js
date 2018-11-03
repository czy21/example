import request from './request'

export default {

  // 获取分页用户列表
  getPageUsers: (pageIndex, pageSize) => {
    return request.get('user/getPageUsers', {pageIndex: pageIndex, pageSize: pageSize});
  },

  // 添加用户
  create: (data) => {
    return request.post('user/create', data);
  },

  // 修改用户
  edit: (data) => {
    return request.put('user/edit', data);
  },

  // 删除用户
  del: (id) => {
    return request.delete('user/delete', {id: id});
  },

  // 为用户分配角色
  saveUserRoles(userId, userRoles) {
    return request.post('/user/saveUserRoles', {userId: userId, userRoles: userRoles});
  },

  // 获取用户角色
  getUserRoles(userId) {
    return request.get('user/getUserRoles', {userId: userId});
  }
}


