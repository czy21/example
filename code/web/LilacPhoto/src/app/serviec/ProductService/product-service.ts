import { Injectable } from '@angular/core';
import { HttpService } from '../HttpSerivce/http.service';
import { Observable, of } from 'rxjs';
import { ProductDto } from 'src/app/Model/Products';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpService: HttpService) { }
  
  Get(productId: number): Observable<ProductDto[]> { 

    let query = `Product/?id=${productId}`;

    return this.httpService.get<ProductDto[]>(query);
  }
}
