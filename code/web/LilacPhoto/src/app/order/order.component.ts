import { Component, OnInit } from '@angular/core';
import { OderService } from '../serviec/OrderService/oder.service';
import { OrderDto } from '../Model/OrderModels';
import { UserService } from '../serviec/UserService/user.service';
import { url } from '../serviec/HttpSerivce/http.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {
  type : number = 0;
  order: Array<OrderDto>;

  constructor(private orderService: OderService, private userService: UserService) { 
    this.order = new Array<OrderDto>();
    this.getOrderList(this.type);
  }

  ngOnInit() {
  }
  
  order_type(id) {
    this.type = Number(id);
    this.getOrderList(this.type);
  }

  checkState(paied: boolean) { 
    return paied ? '未支付' : '支付成功';
  }

  getPic(path: string): string { 
    return url + path;
  }

  getOrderList(type:number) { 
    this.orderService.getOders(
      this.userService.userModel.UserId,
      type
    ).subscribe(re => {
      console.log(re);
      Object.assign(this.order, re);
      console.log(this.order);
    });
  }
}
