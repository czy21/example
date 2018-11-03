import request from './request';

// 加载控制器列表
export default {
  loadController: () => {
    return request.get('admin/loadController');
  }
}
