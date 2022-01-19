import Vue from 'vue'
import VueRouter, {RouteConfig} from 'vue-router'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
    {
        path: '/',
        name: 'Home',
        component: () => import('@v/layout/Home.vue'),
        children: [
            {
                name: "人员管理",
                path: "user",
                component: () => import('@v/pages/user/index.vue')
            },
            {
                name: "菜单管理",
                path: "menu",
                component: () => import('@v/pages/menu/index.vue')
            },
        ]
    },
]


const router = new VueRouter({
    mode: 'history',
    base:"/",
    routes
})

export default router
