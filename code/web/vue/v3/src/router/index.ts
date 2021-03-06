import {createRouter, createWebHistory} from 'vue-router'
import Home from '@/layout/Home.vue'

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        // children: [
        //     {
        //         name: "人员管理",
        //         path: "user",
        //         component: () => import('@v/page/user/index.vue')
        //     },
        //     {
        //         name: "菜单管理",
        //         path: "menu",
        //         component: () => import('@v/page/menu/index.vue')
        //     },
        // ]
    },
]
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router
