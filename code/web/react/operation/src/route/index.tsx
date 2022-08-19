import User from "@/view/user"
import Menu from "@/view/menu/initMenu"
import {RouteObject} from "react-router-dom";

const routes: RouteObject[] = [
    {
        path: "/user",
        element: <User/>,
    },
    {
        path: "/menu",
        element: <Menu/>,
    }
];
export default routes