import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";

export const url = 'https://photo.hzrxkjgs.cn/'

@Injectable({
  providedIn: "root",
})
export class HttpService {
  constructor(private http: HttpClient) {}

  get<T>(e): Observable<T> {
    return this.http.get<T>(url + e);
  }

  post<T>(query: string, body: any): Observable<T> {
    return this.http.post<T>(url + query, body);
  }
}
