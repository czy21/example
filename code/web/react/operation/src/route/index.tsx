import A from "@v/A";
import B from "@v/B";
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