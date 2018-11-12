import Home from '@/views/common/Home'
import Login from '@/views/Login'

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
    children: []
  },
]

