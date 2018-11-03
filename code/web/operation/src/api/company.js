import request from './request';


export default {
  // 获取公司列表
  getCompanies: () => {
    return request.get('company/getCompanies');
  }
}
