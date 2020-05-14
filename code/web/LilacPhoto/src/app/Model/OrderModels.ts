export class OrderDto { 
    constructor(
        public id?: number,
        // 订单名
        public name?: string,
        // 订单总额
        public money?: number,
        // 支付时间
        public payDate?: Date,
        // 图片地址
        public picturePath?: string,
        // 订单号
        public guid?: string,
        // 店铺名
        public shopName?: string,
        // 店铺ID
        public shopId?: number,
        // 用户姓名
        public userName?: string,
        // 用户
        public userId?: number,
        // 备注
        public remark?: string,
        // 产品属性ID
        public productInfoId?: number,
        // 产品属性名
        public productInfoName?: string,
        // 预定时间
        public bookTime?: Date,
        // 产品名
        public ProductName?: string,
        // 是否支付
        public paied?: boolean,
        // 首页展示图
        public homePic?: string,
        // 订单状态
        public state?:OrderState
    ) { }
}

export enum OrderState
{
    待支付,已支付,已完成
}