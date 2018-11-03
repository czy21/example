import request from './request';

export default {


  // 获取角色列表
  getRoles: () => {
    return request.get('role/getRoles');
  },

// 获取分页角色列表
  getPageRoles:(pageIndex, pageSize)=> {
    return request.get('role/getPageRoles', {pageIndex: pageIndex, pageSize: pageSize});
  },

// 保存角色菜单关联关系
  saveRoleMenus:(roleId, roleMenus)=> {
    return request.post('role/saveRoleMenus', {roleId: roleId, roleMenus: roleMenus});
  },

// 保存角色权限关联关系
  saveRoleActions:(roleId, roleActions)=> {
    return request.post('role/saveRoleActions', {roleId: roleId, roleActions: roleActions});
  },

// 根据角色获取菜单列表
  getRoleMenus:(roleId)=> {
    return request.get('role/getRoleMenus', {roleId: roleId});
  },

// 根据角色获取菜单列表
  getRoleActions:(roleId)=> {
    return request.get('role/getRoleActions', {roleId: roleId});
  },
// 添加角色
  create: (data) => {
    return request.post('role/create', data);
  },

// 修改角色
  edit: (data) => {
    return request.put('role/edit', data);
  },

// 删除角色
  del: (id) => {
    return request.delete('role/delete', {id: id});
  }

}

