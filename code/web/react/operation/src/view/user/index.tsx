import React from "react";
import {Button} from "antd";
import api from "@/api"

let load = () => {
    // console.log(process.env)
    api.post("user/search", {}).then(data => {
        console.log(data)
    })
}

export default class Index extends React.Component<any, any> {


    render() {
        // return <Table dataSource={this.state.list} columns={columns}/>;
        return <Button onClick={load}>加载</Button>
    }
}