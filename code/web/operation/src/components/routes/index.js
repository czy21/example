import Login from '@v/Login'
import Home from '@v/common/Home'
import system from './system'
import stub from '../'

export default [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login,
  },
  {
    path: '/home',
    component: Home,
    meta: {title: "系统首页"},
    children: [
      ...system
    ],
    beforeEnter(to, from, next) {
      let token = stub.ref.jsUtil.auth.getToken()
      if (!token) {
        next({path: 'login'})
      }
      next()
    }
  },
]

