export class ShopDto { 
    constructor(
        public id?: number,
        public name?: string,
        public adress?: string,
        public phone?: string,
        public open?: number,
        public close?:number
    ) { }
}

export class Schedule { 
    constructor(
        public open?: Date,
        public close?: Date,
        public infos?:Array<ScheduleInfo>
    ) { 
        infos = new Array<ScheduleInfo>();
    }
}

export class ScheduleInfo { 
    constructor(
        public time: Date,
        public flag:boolean
    ) { }
}