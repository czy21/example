import React from "react";
import stub from "@/init";
import {Table, Filter} from 'share-react'


const records: any = []

let load = () => {

    // console.log(process.env)
    stub.api.post("erp-portal/user/search", {}).then(data => {
        console.log(data)
    })
}

const InstanceList: React.FC<any> = (props: any) => {

    const [data, setData] = stub.ref.react.useState<any>({})
    const [query, setQuery] = stub.ref.react.useState<any>()

    stub.ref.react.useEffect(() => {
        // stub.store.dispatch(stub.reducer.action.option.fetch(["dbInstanceKind", "genderKind"]))
        handleSearch()
    }, [])

    const columns = [
        {
            key: 'name',
            header: "名称",
            render: (text: any, record: any) => {
                return (
                    <a onClick={() => {
                        props.history.push(`${props.route.path}/${record.name}`)
                    }}>{record.name}</a>
                )
            }
        },
    ];

    const handleSearch = (q?: any) => {
        setQuery(q)
        setData({"list": [{"name": "czy"}]})
        // stub.api.post("db/instance/search", stub.ref.lodash.omit(q, "total")).then((t: any) => setData(t.data))
    }
    const filter = (
        <Filter
            filters={[
                {
                    "key": "name",
                    "label": "名称"
                },
                {
                    "key": "address",
                    "label": "地址"
                },
            ]}
            onSearch={handleSearch}
            page={data.page}
        />
    )
    const [instanceAddVisible, setInstanceAddVisible] = stub.ref.react.useState<boolean>(false);

    return (
        <Table
            filter={filter}
            columns={columns}
            list={data.list}
            page={data.page}
        />
    )

}

export default InstanceList