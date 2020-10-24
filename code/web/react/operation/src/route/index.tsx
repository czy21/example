import A from "../view/A";
import B from "../view/B";
import User from "@v/user"
import {RouteConfig} from "react-router-config";

const routes: RouteConfig[] = [
    {
        path: "/system",
        component: A,
    },
    {
        path: "/b",
        component: B,
    },
    {
        path: "/user",
        component: User,
    }

];
export default routes