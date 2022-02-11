import {AppConfig as WechatAppConfig} from 'remax/wechat';
import {AppConfig as AliAppConfig} from 'remax/ali';

export const globalAppConfig: any = {
    title: "Demo",
    color: '#282c34',
    pages: [
        'pages/index/index'
    ]
}
export const wechat: WechatAppConfig = {
    pages: globalAppConfig.pages,
    window: {
        navigationBarTitleText: globalAppConfig.title,
        navigationBarBackgroundColor: globalAppConfig.color,
    },
};

export const ali: AliAppConfig = {
    pages: globalAppConfig.pages,
    window: {
        defaultTitle: globalAppConfig.title,
        titleBarColor: globalAppConfig.color,
    },
};
