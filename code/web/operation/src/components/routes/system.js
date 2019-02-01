import UserList from '@v/system/UserList'
import MenuList from '@v/system/MenuList'
import RoleList from '@v/system/RoleList'
import DepartmentList from '@v/system/DepartmentList'
import LogList from '@v/system/LogList'
import CompanyList from '@v/system/CompanyList'
import SvgList from '@v/general/SvgList'
import Privilege from '@v/system/Privilege'

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
    name: "CompanyList",
    path: '/system/company',
    component: CompanyList,
    meta: {title: "公司管理"}
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
  {
    name: "SvgList",
    path: '/system/svg',
    component: SvgList,
    meta: {title: "Svg图标"}
  },
  {
    name: "Privilege",
    path: '/system/privilege',
    component: Privilege,
    meta: {title: "特权操作"}
  },
]
