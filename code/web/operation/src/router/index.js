import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [{
    path: '/',
    redirect: '/Home'
  },
    {
      path: '/Home',
      redirect: '/User'
    },
    {
      path: '/Login',
      component: () => import('@/views/page/Login'),
    },
    {
      path: '/Home',
      component: () => import('@/views/common/Home'),
      meta: {title: "系统首页"},
      children: [
        {
          path: '/Company',
          component: () => import('@/views/page/manage/company'),
          meta: {title: "公司管理"}
        },
        {
          path: '/User',
          component: () => import('@/views/page/manage/user'),
          meta: {title: "用户管理"}
        },
        {
          path: '/Role',
          component: () => import('@/views/page/manage/role'),
          meta: {title: "角色管理"}
        },
        {
          path: '/Menu',
          component: () => import('@/views/page/manage/menu'),
          meta: {title: "菜单管理"}
        },
        {
          path: '/Depot',
          component: () => import('@/views/page/manage/depot'),
          meta: {title: "仓库管理"}
        },
        {
          path: '/Department',
          component: () => import('@/views/page/manage/department'),
          meta: {title: "部门管理"}
        },
        {
          path: '/Material',
          component: () => import('@/views/page/manage/material'),
          meta: {title: "商品管理"}
        },
        {
          path: '/MaterialAnalysis',
          component: () => import('@/views/page/manage/material/MaterialAnalysis'),
          meta: {title: "商品统计"}
        },
        {
          path: '/Category',
          component: () => import('@/views/page/manage/category'),
          meta: {title: "商品类别管理"}
        },
      ]
    },
  ]
})
