import { Injectable } from '@angular/core';
import { HttpService } from '../HttpSerivce/http.service';
import { Observable } from 'rxjs';
import { OrderDto, OrderState } from 'src/app/Model/OrderModels';

@Injectable({
  providedIn: 'root'
})
export class OderService {

  constructor(private httpClient: HttpService) { }
  
  getOders(userId: number,type:OrderState): Observable<Array<OrderDto>> { 
    let query = `Order/?personId=${userId}&state=${type}`;

    return this.httpClient.get<Array<OrderDto>>( query);
  }
}
