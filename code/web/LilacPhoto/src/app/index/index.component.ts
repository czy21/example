import { Component, OnInit } from '@angular/core';
import { HttpService } from '../serviec/HttpSerivce/http.service';
import { of } from 'rxjs';
@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {

  constructor(public http: HttpService) { }

  ngOnInit() {
    this.getDate();
    // tslint:disable-next-line: only-arrow-functions

  }
  getDate() {
    this.http.get(`Product/?id= 0`).subscribe((re: any) => {
      console.log(re);
    });
  }
}
