import A from "../view/A";
import B from "../view/B";
import User from "@v/user"
import {ContactsOutlined, SettingOutlined} from '@ant-design/icons';
import React from "react"

export interface RouteModel {
    name: string,
    path: string
    icon: React.ReactNode
    component?: React.ComponentType<any>
    children?: Array<RouteModel>
}

const routes: RouteModel[] = [
    {
        name: "FlatA",
        path: "/system",
        icon: <SettingOutlined/>,
        component: A,
    },
    {
        name: "FlatB",
        path: "/b",
        icon: <ContactsOutlined/>,
        component: B,
    },
    {
        name: "用户管理",
        path: "/user",
        icon: <ContactsOutlined/>,
        component: User,
    }
];
export default routes