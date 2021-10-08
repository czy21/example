import React from "react";
import stub from "@/init";


const records: any = []

let load = () => {
    // console.log(process.env)
    stub.api.post("erp-portal/user/search", {}).then(data => {
        console.log(data)
    })
}

export default class Index extends React.Component<any, any> {
    render() {
        // return <Table dataSource={this.state.list} columns={columns}/>;
        return <stub.ref.antd.Button onClick={load}>加载</stub.ref.antd.Button>
    }
}