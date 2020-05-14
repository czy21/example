import {Component, OnInit} from '@angular/core';
import {ShopService} from '../serviec/ShopService/shop-service';
import {Schedule, ScheduleInfo, ShopDto} from '../Model/Shops';
import {CartService} from '../serviec/shopCart/cart.service';

@Component({
  selector: 'app-adress',
  templateUrl: './adress.component.html',
  styleUrls: ['./adress.component.scss']
})
export class AdressComponent implements OnInit {
  showPayButton = false;
  week: number[] = [0, 1, 2, 3, 4, 5, 6];
  shops: ShopDto[];
  adress: boolean = true;
  // 时间表
  schedule: Schedule;
  showSchedule: boolean = false;
  selectDay;
  selectTime;

  constructor(
    private shopService: ShopService,
    private order: CartService
  ) {
    this.shops = new Array<ShopDto>();
    this.schedule = new Schedule();

    this.shopService.Get().subscribe(re => {
      var internalShops = new Array<ShopDto>();
      let dto1 = new ShopDto();
      dto1.id = "1"
      dto1.name = "nishishei"

      let dto2 = new ShopDto();
      dto1.id = "2"
      dto1.name = "nishishei2"

      let dto3 = new ShopDto();
      dto3.id = "1"
      dto3.name = "nishishei"

      let dto4 = new ShopDto();
      dto4.id = "1"
      dto4.name = "nishishei"

      internalShops.push(dto1);

      internalShops.push(dto2);

      internalShops.push(dto3);

      internalShops.push(dto4);

      Object.assign(this.shops, internalShops);
    });

  }

  ngOnInit(): void {
  }

  SelectShop(shopId: number): void {
    this.order.order.shopId = shopId;
    this.adress = false;
  }

  GetDateStr(AddDayCount: number): string {
    var dd = new Date();
    dd.setDate(dd.getDate() + AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth() + 1;//获取当前月份的日期
    var d = dd.getDate();
    return y + '-' + (m < 10 ? '0' + m : m) + '-' + d;
  }

  SelectDay(date: string): void {
    this.showSchedule = true;

    this.shopService.GetSchedule(this.order.order.shopId, date).subscribe(re => {
      console.log(re);

      Object.assign(this.schedule, re);

      console.log(this.schedule);
    });
  }

  GetScheduInfo(): Array<ScheduleInfo> {
    console.log(this.schedule.infos);
    console.log(this.showSchedule);

    return this.schedule.infos;
  }

  Order(date: Date, signal: boolean): void {
    if (signal) {
      console.log(signal);
      console.log(date);

      this.order.order.bookTime = date;

      this.order.Post().subscribe(re => {
        console.log(re);
      })
    }
  }
}
