import User from "@v/user"
import Menu from "@v/user/initMenu"
import {RouteConfig} from "react-router-config";

const routes: RouteConfig[] = [
    {
        path: "/user",
        component: User,
    },
    {
        path: "/menu",
        component: Menu,
    }

];
export default routes