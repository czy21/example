import request from './request'

export default {

  // 校验用户登录
  checkLogin:(loginName, password)=> {
    return request.post('login', {loginName: loginName, password: password});
  }

}


