import {ContactsOutlined, SettingOutlined} from '@ant-design/icons';
import React from "react"

export interface MenuModel {
    name: string,
    path: string
    icon: React.ReactNode
    children?: Array<MenuModel>
}

const menus: MenuModel[] = [
    {
        name: "FlatA",
        path: "/system",
        icon: <SettingOutlined/>,
    },
    {
        name: "FlatB",
        path: "/b",
        icon: <ContactsOutlined/>,
    },
    {
        name: "用户管理",
        path: "/user",
        icon: <ContactsOutlined/>,
    }
];
export default menus