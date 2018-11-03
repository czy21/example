import request from './request';


export default {

  //根据公司Id获取部门列表
  getCompanyDeps: (companyId) => {
    return request.get('department/getCompanyDeps', {companyId: companyId});
  },
  //获取部门列表
  getDepartments: () => {
    return request.get('department/getDepartments');
  },
  //添加部门
  create: (data) => {
    return request.post('department/create', data);
  },
  //修改部门
  edit: (data) => {
    return request.put('department/edit', data);
  },
  //删除部门
  del: (id) => {
    return request.delete('department/delete', {id: id});
  }
};



