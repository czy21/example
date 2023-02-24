import React from "react";
import stub from "@/init";
let load = () => {
    stub.api.post("gw1/demo-portal/user/search", {})
        .then(data => {
        console.log(data)
    })
}
export default class Index extends React.Component<any, any> {
    render() {
        // return <Table dataSource={this.state.list} columns={columns}/>;
        return (
            <div>
                <stub.ref.antd.Button onClick={load}>加载</stub.ref.antd.Button>
                <stub.ref.antd.Button onClick={()=>{
                    console.log("hahaha")
                }}>生成菜单</stub.ref.antd.Button>
            </div>
        )
    }
}
