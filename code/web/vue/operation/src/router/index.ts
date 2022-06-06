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
                component: () => import('@v/page/user/index.vue')
            },
            {
                name: "菜单管理",
                path: "menu",
                component: () => import('@v/page/menu/index.vue')
            },
        ]
    },
]


const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
