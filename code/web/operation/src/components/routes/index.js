import Login from '@/views/Login'
import Home from '@/views/common/Home'
import system from './system'

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
    ]
  },
]

