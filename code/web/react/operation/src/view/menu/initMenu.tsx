import React from "react";
import stub from "@/init";


const records: any = []

let load = () => {
    // console.log(process.env)
    stub.api.post("erp-portal/user/search", {}).then(data => {
        console.log(data)
    })
}

let generateMenu = () => {
    let data =[
        {
            "code": "kfwl_basicData",
            "name": "基础数据管理",
            "attr": {
                "path": "/pharmacySystem/basicData",
                "name": "basicData",
                "component": "Index",
                "icon": "el-icon-s-data",
                "meta": {
                    "isMenu": true
                }
            },
            "children": [
                {
                    "code": "kfwl_pharmacySystemDetail",
                    "name": "药房体系管理",
                    "attr": {
                        "path": "/pharmacySystem/basicData/pharmacySystemDetail",
                        "name": "pharmacySystemDetail",
                        "component": "basicData/pharmacySystemDetail/pharmacySystemDetail",
                        "meta": {
                            "isMenu": true
                        }
                    },
                    "children": [],
                    "functions": [
                        {
                            "code": "kfwl_select_pharmacySystem",
                            "name": "查看"
                        },
                        {
                            "code": "kfwl_add_pharmacySystem",
                            "name": "新建"
                        },
                        {
                            "code": "kfwl_edit_pharmacySystem",
                            "name": "修改"
                        },
                        {
                            "code": "kfwl_delete_pharmacySystem",
                            "name": "删除"
                        }
                    ]
                },
                {
                    "code": "kfwl_pharmacyManages",
                    "name": "药房管理",
                    "attr": {
                        "path": "/pharmacySystem/basicData/pharmacyManages",
                        "name": "pharmacyManages",
                        "component": "basicData/PharmacyManages/PharmacyManages",
                        "meta": {
                            "isMenu": true
                        }
                    },
                    "children": [],
                    "functions": [
                        {
                            "code": "kfwl_select_pharmacyManages",
                            "name": "查看"
                        },
                        {
                            "code": "kfwl_add_pharmacyManages",
                            "name": "新建"
                        },
                        {
                            "code": "kfwl_edit_pharmacyManages",
                            "name": "修改"
                        },
                        {
                            "code": "kfwl_delete_pharmacyManages",
                            "name": "删除"
                        }
                    ]
                },
                {
                    "code": "kfwl_medicineManage",
                    "name": "药品管理",
                    "attr": {
                        "path": "/pharmacySystem/basicData/medicineManage",
                        "name": "medicineManage",
                        "component": "basicData/medicineManage/medicineManage",
                        "meta": {
                            "isMenu": true
                        }
                    },
                    "children": [],
                    "functions": [
                        {
                            "code": "kfwl_select_medicineManage",
                            "name": "查看"
                        },
                        {
                            "code": "kfwl_add_medicineManage",
                            "name": "新建"
                        },
                        {
                            "code": "kfwl_edit_medicineManage",
                            "name": "修改"
                        },
                        {
                            "code": "kfwl_delete_medicineManage",
                            "name": "删除"
                        }
                    ]
                }
            ],
            "functions": [
                {
                    "code": "kfwl_select_basicData",
                    "name": "查看"
                }
            ]
        },
        {
            "code": "kfwl_businessData",
            "name": "业务数据管理",
            "attr": {
                "path": "/pharmacySystem/businessData",
                "name": "businessData",
                "component": "Index",
                "icon": "el-icon-s-order",
                "meta": {
                    "isMenu": true
                }
            },
            "children": [
                {
                    "code": "kfwl_pharmacyOrdermanage",
                    "name": "药房订单管理",
                    "attr": {
                        "path": "/pharmacySystem/businessData/pharmacyOrdermanage",
                        "name": "pharmacyOrdermanage",
                        "component": "businessData/PharmacyOrdermanage/PharmacyOrdermanage",
                        "meta": {
                            "isMenu": true
                        }
                    },
                    "children": [],
                    "functions": [
                        {
                            "code": "kfwl_select_pharmacyOrdermanage",
                            "name": "查看"
                        }
                    ]
                },
                {
                    "code": "kfwl_drugOrderDetail",
                    "name": "药房订单管理详情",
                    "attr": {
                        "path": "/pharmacySystem/businessData/drugOrderDetail",
                        "name": "drugOrderDetail",
                        "component": "businessData/PharmacyOrdermanage/drugOrderDetail",
                        "meta": {
                            "activeMenu": "/pharmacySystem/businessData/pharmacyOrdermanage"
                        }
                    },
                    "children": [],
                    "functions": null
                },
                {
                    "code": "kfwl_logManage",
                    "name": "日志管理",
                    "attr": {
                        "path": "/pharmacySystem/businessData/logManage",
                        "name": "logManage",
                        "component": "businessData/logManage/logManage",
                        "meta": {
                            "isMenu": true
                        }
                    },
                    "children": [],
                    "functions": [
                        {
                            "code": "kfwl_select_logManage",
                            "name": "查看"
                        }
                    ]
                },
                {
                    "code": "kfwl_logDetail",
                    "name": "日志管理详情",
                    "attr": {
                        "path": "/pharmacySystem/businessData/logDetail",
                        "name": "logDetail",
                        "component": "businessData/logManage/logDetail",
                        "meta": {
                            "activeMenu": "/pharmacySystem/businessData/logManage"
                        }
                    },
                    "children": [],
                    "functions": null
                }
            ],
            "functions": [
                {
                    "code": "kfwl_select_businessData",
                    "name": "查看"
                }
            ]
        }
    ]
    let insertSQLs: any[] = []
    function recursive(items: any, parent: any, systemId: any, isInitViewFunc: boolean) {
        let menuWhereCodeFunc = (menuCode: any) => `(select m.id from upms_menu m where m.code = '${menuCode}' and m.system_id in ${systemId})`
        let menuInsertTemplateFunc =
            (code: any, name: any, attr: any, parentId: any, systemId: any) =>
                `INSERT INTO upms_menu(code, name,attr,parent_id,system_id)values('${code}','${name}','${attr}',${parentId},${systemId})`
        let funcInsertTemplateFunc =
            (code: any, name: any, menuId: any, systemId: any) =>
                `insert into upms_function(code,name,menu_id,system_id)values('${code}','${name}',${menuId},${systemId})`
        items.forEach((t: any) => {
            let parentCode: string = parent?.code.trim()
            let parentSQL: null | string = parentCode === undefined ? null : menuWhereCodeFunc(parentCode)
            insertSQLs.push(menuInsertTemplateFunc(
                t.code.trim(),
                t.name.trim(),
                JSON.stringify(t.attr),
                parentSQL,
                systemId)
            )
            if (!stub.ref.lodash.isEmpty(t.functions)) {
                t.functions.forEach((f: any) => {
                    insertSQLs.push(funcInsertTemplateFunc(
                        f.code.trim(),
                        f.name.trim(),
                        menuWhereCodeFunc(t.code),
                        systemId)
                    )
                })
            }
            if (!stub.ref.lodash.isEmpty(t.children)) {
                recursive(t.children, t, systemId,isInitViewFunc)
            } else {
                if (isInitViewFunc){
                    insertSQLs.push(funcInsertTemplateFunc(
                        t.code.trim() + "_view",
                        "查看",
                        menuWhereCodeFunc(t.code),
                        systemId
                    ))
                }
            }
        })
    }

    recursive(data, undefined, `(select s.id from upms_system s where s.code = 'kfwl')`, false)
    // recursive(kf, undefined, `(select s.id from upms_system s where s.code = 'kf')`, true)
    console.log(insertSQLs.join(";\n"))
}

export default class Index extends React.Component<any, any> {
    render() {
        // return <Table dataSource={this.state.list} columns={columns}/>;
        return (
            <div>
                <stub.ref.antd.Button onClick={load}>加载</stub.ref.antd.Button>
                <stub.ref.antd.Button onClick={generateMenu}>生成菜单</stub.ref.antd.Button>
            </div>
        )
    }
}