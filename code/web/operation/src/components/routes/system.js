import CompanyList from '@/views/system/company'
import UserList from '@/views/system/UserList'
import MenuList from '@/views/system/MenuList'
import RoleList from '@/views/system/RoleList'

export default [
  // {
  //   name: "CompanyList",
  //   path: '/system/company',
  //   component: CompanyList,
  //   meta: {title: "公司管理"}
  // },
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
]
