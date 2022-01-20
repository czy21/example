import Vue from 'vue'
import VueRouter, {RouteConfig} from 'vue-router'
import Home from '@v/layout/Home.vue'
import User from '@v/pages/user/index.vue'
import Menu from '@v/pages/menu/index.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        children: [
            {
                name: "人员管理",
                path: "user",
                component: User
            },
            {
                name: "菜单管理",
                path: "menu",
                component: Menu
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
