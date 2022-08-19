import React from "react";
import {Layout} from "antd";
import routes from "@/route";
import {useRoutes} from "react-router-dom";

const AntdContent = Layout.Content;

function RouteElement() {
    return useRoutes(routes)
}

export default class Content extends React.Component<any, any> {
    render() {
        return (
            <AntdContent className="content">
                <RouteElement/>
            </AntdContent>
        )
    }
}