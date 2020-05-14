export class ProductModel { 
    constructor(
          public id?: number,
        // 产品名
        public name?: string,
        // 金额
        public money?: number,
        // 首页图片
        public homePage?: string,
        // 内容图片
        public pages?: Array<string>,
        // 产品内容
        public info?: string,
        // 产品属性
        public infos?:Array<ProductInfo>
    ) {
    }
}

export class ProductDto { 
    constructor(
          public id?: number,
        // 产品名
        public name?: string,
        // 金额
        public money?: number,
        // 首页图片
        public homePage?: string,
        // 内容图片
        public pages?: string,
        // 产品内容
        public info?: string,
        // 产品属性
        public infos?:Array<ProductInfo>
    ) {
    }
}

export class ProductInfo { 
    constructor(
        public id?: number,
        public name?: string,
        public price?:number,
        public productId?:number
    ) { }
}