import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  CARTID: any
  constructor(private http: HttpClient) { }

  checkout(CARTID: any){
    return this.http.post("http://localhost:8085/api/v1/payment/placefood",{"cart_id": `${CARTID}`});
  }
}
