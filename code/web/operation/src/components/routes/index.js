export default [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: () => import('@/views/Login'),
  },
  {
    path: '/home',
    component: () => import('@/views/common/Home'),
    meta: {title: "系统首页"},
    children: []
  },
]

