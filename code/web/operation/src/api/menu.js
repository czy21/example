import request from './request'

export default {

  // 根据条件获取分页权限列表
  getPageMenuActions: (page) => {
    return request.post('menu/getPageMenuActions', page);
  },

  // 获取用户菜单列表
  getUserMenus: (loginName) => {
    return request.get('menu/getUserMenus', {loginName: loginName});
  },

  // 获取树形权限列表
  getActionTree: () => {
    return request.get('menu/getActionTree');
  },

  getMenuActions: (parentId, pageIndex, pageSize) => {
    return request.get('menu/getMenuActions', {parentId: parentId, pageIndex: pageIndex, pageSize: pageSize});
  },

  // 添加菜单
  create: (data) => {
    return request.post('menu/create', data);
  },

  batchAddAction: (actions) => {
    return request.post('menu/batchAddAction', {actions: actions});
  },

  // 修改菜单
  edit: (data) => {
    return request.put('menu/edit', data);
  },

  // 删除菜单
  del: (id) => {
    return request.delete('menu/delete', {id: id});
  }
}
