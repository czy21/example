import 'antd/dist/antd.less'
import '@/assets/less/Home.less'
import React from "react";
import {Layout} from 'antd';
import * as intl from 'react-intl'
import Sider from '@/layout/Sider'
import Header from '@/layout/Header'
import Content from '@/layout/Content'

const Home: React.FC<any> = (props: any) => {
    return (
        // <intl.IntlProvider locale={"en"} messages={props.locale.message && props.locale.message[props.locale.key]} defaultLocale={"en"}>
            <Layout>
                <Sider/>
                <Layout className={"container"}>
                    <Header/>
                    <Content/>
                </Layout>
            </Layout>
        // </intl.IntlProvider>
    );
}
export default Home