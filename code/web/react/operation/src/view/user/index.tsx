import React from "react";
import {Button, Table} from "antd";
import stub from "@/init";


const records: any = []

let load = () => {
    // console.log(process.env)
    // api.post("portal/user/search", {}).then(data => {
    //     console.log(data)
    // })
}

export default class Index extends React.Component<any, any> {


    render() {
        // return <Table dataSource={this.state.list} columns={columns}/>;
        return <Button onClick={load}>加载</Button>
    }
}