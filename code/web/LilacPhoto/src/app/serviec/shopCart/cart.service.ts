import { Injectable } from '@angular/core';
import { OrderDto } from 'src/app/Model/OrderModels';
import { HttpService } from '../HttpSerivce/http.service';
import { Observable } from 'rxjs';
import { UserService } from '../UserService/user.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  order: OrderDto;

  constructor(
    private httpClient: HttpService,
    private user: UserService
  ) {
    this.order = new OrderDto();
    this.order.userId = this.user.userModel.UserId;
  }

  // 提交订单
  Post(): Observable<number> { 
    console.log(this.order);
    
    let url = 'Order';

    return this.httpClient.post<number>(url, this.order);
  }
}
