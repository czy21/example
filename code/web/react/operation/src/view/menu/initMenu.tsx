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
    let data = [
        {
            code: 'insure_project_manage',
            name: '项目管理',
            attr: {
                name: 'projectManage',
                path: '/projectManage',
                icon: 'project',
                meta: {
                    title: '项目管理'
                }
            },
            children: [
                {
                    code: 'insure_project',
                    name: '项目管理',
                    attr: {
                        name: 'project',
                        path: '/project',
                        component: 'pages/project/list.vue',
                        meta: {
                            title: '项目管理'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_project_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_project_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_project_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_project_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_project_detail',
                    name: '项目详情',
                    attr: {
                        name: 'projectDetail',
                        path: '/project/detail',
                        component: 'pages/project/detail.vue',
                        meta: {
                            title: '项目详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                }
            ]
        },
        {
            code: 'insure_project_config',
            name: '项目配置',
            attr: {
                name: 'projectConfig',
                path: '/projectConfig',
                icon: 'projectConfig',
                meta: {
                    title: '项目配置'
                }
            },
            children: [
                {
                    code: 'insure_plan',
                    name: '计划管理',
                    attr: {
                        name: 'plan',
                        path: '/plan',
                        component: 'pages/projectConfig/plan/list.vue',
                        meta: {
                            title: '计划管理'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_plan_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_plan_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_plan_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_plan_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_plan_detail',
                    name: '计划管理',
                    attr: {
                        name: 'planDetail',
                        path: '/plan/detail',
                        component: 'pages/projectConfig/plan/detail.vue',
                        meta: {
                            title: '计划管理',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                },
                {
                    code: 'insure_product',
                    name: '产品管理',
                    attr: {
                        name: 'product',
                        path: '/product',
                        component: 'pages/projectConfig/product/list.vue',
                        meta: {
                            title: '产品管理'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_product_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_product_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_product_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_product_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_product_detail',
                    name: '产品详情',
                    attr: {
                        name: 'productDetail',
                        path: '/product/detail',
                        component: 'pages/projectConfig/product/detail.vue',
                        meta: {
                            title: '产品详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                },
                {
                    code: 'insure_app',
                    name: '应用管理',
                    attr: {
                        name: 'app',
                        path: '/app',
                        component: 'pages/projectConfig/app/list.vue',
                        meta: {
                            title: '应用管理'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_app_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_app_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_app_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_app_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_app_detail',
                    name: '应用详情',
                    attr: {
                        name: 'appDetail',
                        path: '/app/detail',
                        component: 'pages/projectConfig/app/detail.vue',
                        meta: {
                            title: '应用详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                },
                {
                    code: 'insure_index_config',
                    name: '投保首页配置',
                    attr: {
                        name: 'indexConfig',
                        path: '/indexConfig',
                        component: 'pages/projectConfig/indexConfig/list.vue',
                        meta: {
                            title: '投保首页配置'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_index_config_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_index_config_update',
                            name: '修改'
                        }
                    ]
                },
                {
                    code: 'insure_common_page',
                    name: '通用页面',
                    attr: {
                        name: 'commonPage',
                        path: '/commonPage',
                        component:
                            'pages/projectConfig/app/commonPage/list.vue',
                        meta: {
                            title: '通用页面'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_common_page_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_common_page_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_common_page_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_common_page_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_common_page_detail',
                    name: '通用页面详情',
                    attr: {
                        name: 'commonPageDetail',
                        path: '/commonPage/detail',
                        component:
                            'pages/projectConfig/app/commonPage/detail.vue',
                        meta: {
                            title: '通用页面详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                },
                {
                    code: 'insure_theme',
                    name: '主题管理',
                    attr: {
                        name: 'theme',
                        path: '/theme',
                        component: 'pages/projectConfig/app/theme/list.vue',
                        meta: {
                            title: '主题管理'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_theme_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_theme_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_theme_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_theme_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_theme_detail',
                    name: '主题详情',
                    attr: {
                        name: 'themeDetail',
                        path: '/theme/detail',
                        component: 'pages/projectConfig/app/theme/detail.vue',
                        meta: {
                            title: '主题详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                }
            ]
        },
        {
            code: 'insure_wxmp_config',
            name: '公众号配置',
            attr: {
                name: 'wxmpConfig',
                path: '/wxmpConfig',
                icon: 'wxmpConfig',
                meta: {
                    title: '公众号配置'
                }
            },
            children: [
                {
                    code: 'insure_wx_menu',
                    name: '公众号菜单管理',
                    attr: {
                        name: 'wxMenu',
                        path: '/wxMenu',
                        component: 'pages/wxmpConfig/wxMenu/list.vue',
                        meta: {
                            title: '公众号菜单管理'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_wx_menu_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_wx_menu_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_wx_menu_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_wx_menu_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_wx_menu_detail',
                    name: '公众号菜单详情',
                    attr: {
                        name: 'wxMenuDetail',
                        path: '/wxMenu/detail',
                        component: 'pages/wxmpConfig/wxMenu/detail.vue',
                        meta: {
                            title: '公众号菜单详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                },
                {
                    code: 'insure_wx_auto_res',
                    name: '微信自动回复',
                    attr: {
                        name: 'wxAutoRes',
                        path: '/wxAutoRes',
                        component: 'pages/wxmpConfig/wxAutoRes/list.vue',
                        meta: {
                            title: '微信自动回复'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_wx_auto_res_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_wx_auto_res_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_wx_auto_res_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_wx_auto_res_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_wx_auto_res_detail',
                    name: '微信自动回复详情',
                    attr: {
                        name: 'wxAutoResDetail',
                        path: '/wxAutoRes/detail',
                        component: 'pages/wxmpConfig/wxAutoRes/detail.vue',
                        meta: {
                            title: '微信自动回复详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                },
                {
                    code: 'insure_wx_scene',
                    name: '场景值管理',
                    attr: {
                        name: 'wxScene',
                        path: '/wxScene',
                        component: 'pages/wxmpConfig/wxScene/list.vue',
                        meta: {
                            title: '场景值管理'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_wx_scene_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_wx_scene_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_wx_scene_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_wx_scene_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_wx_scene_detail',
                    name: '场景值详情',
                    attr: {
                        name: 'wxSceneDetail',
                        path: '/wxScene/detail',
                        component: 'pages/wxmpConfig/wxScene/detail.vue',
                        meta: {
                            title: '场景值详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                },
                {
                    code: 'insure_wx_fodder',
                    name: '素材管理',
                    attr: {
                        name: 'wxFodder',
                        path: '/wxFodder',
                        component: 'pages/wxmpConfig/wxFodder/list.vue',
                        meta: {
                            title: '素材管理'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_wx_fodder_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_wx_fodder_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_wx_fodder_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_wx_fodder_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_wx_fodder_detail',
                    name: '素材详情',
                    attr: {
                        name: 'wxFodderDetail',
                        path: '/wxFodder/detail',
                        component: 'pages/wxmpConfig/wxFodder/detail.vue',
                        meta: {
                            title: '素材详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                },
                {
                    code: 'insure_wx_subscribe',
                    name: '订阅消息管理',
                    attr: {
                        name: 'wxSubscribe',
                        path: '/wxSubscribe',
                        component: 'pages/wxmpConfig/wxSubscribe/list.vue',
                        meta: {
                            title: '订阅消息管理'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_wx_subscribe_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_wx_subscribe_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_wx_subscribe_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_wx_subscribe_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_wx_subscribe_detail',
                    name: '订阅消息详情',
                    attr: {
                        name: 'wxSubscribeDetail',
                        path: '/wxSubscribe/detail',
                        component: 'pages/wxmpConfig/wxSubscribe/detail.vue',
                        meta: {
                            title: '订阅消息详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                }
            ]
        },
        {
            code: 'insure_hmb_manage',
            name: '惠民保管理',
            attr: {
                name: 'hmbManage',
                path: '/hmbManage',
                icon: 'hmbManage',
                meta: {
                    title: '惠民保管理'
                }
            },
            children: [
                {
                    code: 'insure_hmb',
                    name: '模板配置',
                    attr: {
                        name: 'hmb',
                        path: '/hmb',
                        component: 'pages/hmb/list.vue',
                        meta: {
                            title: '模板配置'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_hmb_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_hmb_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_hmb_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_hmb_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_hmb_detail',
                    name: '模板配置详情',
                    attr: {
                        name: 'hmbDetail',
                        path: '/hmb/detail',
                        component: 'pages/hmb/detail.vue',
                        meta: {
                            title: '模板配置详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                }
            ]
        },
        {
            code: 'insure_resource',
            name: '资源管理',
            attr: {
                name: 'resource',
                path: '/resource',
                icon: 'resource',
                meta: {
                    title: '资源管理'
                }
            },
            children: [
                {
                    code: 'insure_wxmp',
                    name: '公众号管理',
                    attr: {
                        name: 'wxmp',
                        path: '/wxmp',
                        component: 'pages/resource/wxmp/list.vue',
                        meta: {
                            title: '公众号管理'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_wxmp_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_wxmp_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_wxmp_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_wxmp_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_wxmp_detail',
                    name: '公众号详情',
                    attr: {
                        name: 'wxmpDetail',
                        path: '/wxmp/detail',
                        component: 'pages/resource/wxmp/detail.vue',
                        meta: {
                            title: '公众号详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                },
                {
                    code: 'insure_pay_account',
                    name: '支付商户管理',
                    attr: {
                        name: 'payAccount',
                        path: '/payAccount',
                        component: 'pages/resource/payAccount/list.vue',
                        meta: {
                            title: '支付商户管理'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_pay_account_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_pay_account_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_pay_account_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_pay_account_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_pay_account_detail',
                    name: '支付商户详情',
                    attr: {
                        name: 'payAccountDetail',
                        path: '/payAccount/detail',
                        component: 'pages/resource/payAccount/detail.vue',
                        meta: {
                            title: '支付商户详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
                },
                {
                    code: 'insure_aisle',
                    name: '通道服务管理',
                    attr: {
                        name: 'aisle',
                        path: '/aisle',
                        component: 'pages/resource/aisle/list.vue',
                        meta: {
                            title: '通道服务管理'
                        }
                    },
                    functions: [
                        //该路由需要设置权限的按钮
                        {
                            code: 'insure_aisle_view',
                            name: '查看'
                        },
                        {
                            code: 'insure_aisle_add',
                            name: '新建'
                        },
                        {
                            code: 'insure_aisle_update',
                            name: '修改'
                        },
                        {
                            code: 'insure_aisle_delete',
                            name: '删除'
                        }
                    ]
                },
                {
                    code: 'insure_aisle_detail',
                    name: '通道服务详情',
                    attr: {
                        name: 'aisleDetail',
                        path: '/aisle/detail',
                        component: 'pages/resource/aisle/detail.vue',
                        meta: {
                            title: '通道服务详情',
                            hidden: true // 不在侧边菜单显示
                        }
                    }
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

    recursive(data, undefined, `(select s.id from upms_system s where s.code = 'insure')`, false)
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