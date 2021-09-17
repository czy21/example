import React from "react";
import {Button, Table} from "antd";
import stub from "@/init";


const records: any = []

let load = () => {
    // console.log(process.env)
    // api.post("portal/user/search", {}).then(data => {
    //     console.log(data)
    // })
    let ret: any = {}
    Object.entries(stub.ref.lodash.groupBy(records, (t: any) => t.code))
        .forEach(([k, v]) => {
            ret[k] = {}
            v.forEach(t => {
                let url = new URL(t.url)
                let list = {
                    "url": url.pathname,
                    "method": "POST"
                }
                let meta = {
                    "url": `/${url.pathname.substring(1).split("/")[0]}/api/v1/get/admin/properties?adminPage=${stub.ref.lodash.camelCase(stub.ref.lodash.join([k, "insurance", t.domain_code], "_"))}`,
                    "method": "GET"

                }
                ret[k][`${t.domain_code}`] = {}
                ret[k][`${t.domain_code}`]["list"] = list
                ret[k][`${t.domain_code}`]["meta"] = meta
            })
        })

    console.log(JSON.stringify(ret))


}

export default class Index extends React.Component<any, any> {


    render() {
        // return <Table dataSource={this.state.list} columns={columns}/>;
        return <Button onClick={load}>加载</Button>
    }
}