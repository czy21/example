import React from "react"
import stub from "@/init";

export interface MenuModel {
    name: string,
    path: string
    icon: React.ReactNode
    children?: Array<MenuModel>
}

const menus: MenuModel[] = [
    {
        name: "用户管理",
        path: "/user",
        icon: <stub.ref.icon.ContactsOutlined/>,
    },
    // {
    //     name: "菜单管理",
    //     path: "/menu",
    //     icon: <stub.ref.icon.ContactsOutlined/>,
    // }
];
export default menus