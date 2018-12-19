import UserList from '@/views/system/UserList'
import MenuList from '@/views/system/MenuList'
import RoleList from '@/views/system/RoleList'
import DepartmentList from '@/views/system/DepartmentList'
import LogList from '@/views/system/LogList'

export default [
  {
    name: "UserList",
    path: '/system/user',
    component: UserList,
    meta: {title: "用户管理"}
  },
  {
    name: "RoleList",
    path: '/system/role',
    component: RoleList,
    meta: {title: "角色管理"}
  },
  {
    name: "MenuList",
    path: '/system/menu',
    component: MenuList,
    meta: {title: "菜单管理"}
  },
  {
    name: "DepartmentList",
    path: '/system/department',
    component: DepartmentList,
    meta: {title: "部门管理"}
  },
  {
    name: "LogList",
    path: '/system/log',
    component: LogList,
    meta: {title: "系统日志"}
  },
]
