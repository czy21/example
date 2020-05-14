import { Injectable } from '@angular/core';
import { HttpService } from '../HttpSerivce/http.service';
import { Observer, Observable } from 'rxjs';
import { ShopDto, Schedule } from 'src/app/Model/Shops';

@Injectable({
  providedIn: 'root'
})
export class ShopService {
  constructor(private httpClient: HttpService) { }
  
  Get(): Observable<Array<ShopDto>>{
    let query = `Shop`

    return this.httpClient.get<Array<ShopDto>>(query);
  }

  GetSchedule(shopId: number, date: string): Observable<Schedule> { 
    let query = `CheckTime/?shopId=${shopId}&date=${date}`;

    return this.httpClient.get<Schedule>(query);
  }

/*   GetCalendar(shopId: number, month: number): Observable<Array<ScheduleInfo>> { 
    let query = `CheckTime/Day/?shopId=${shopId}&month=${month}`;

    return this.httpClient.get<Array<ScheduleInfo>>(query);
  } */
}
