import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { Food } from '../foods/food.model';
import { FoodService } from '../foods/food.service';
import { CartService } from './cart.service';
declare var Razorpay: any;
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  userData!: any
  userId!: any
  FOODNAME: any;
  FOODPRICE: any;
  CARTID: any;
  constructor(public foodService: FoodService, 
              public router: Router, 
              private authService: AuthService,
              private http: HttpClient,
              private cartService: CartService) {
   }


  token = this.authService.getToken();
  requestHeader = new HttpHeaders({
    "Authorization": `Bearer ${this.authService.getToken()}`
  })
  ngOnInit(): void {
    this.getCartDataByUserId();
    this.getCartDataByUserId();
  }

  async getCartDataByUserId(){
    this.userData = this.foodService.getDecodedAccessToken(this.token);
    this.userId = this.userData.id;
    this.http.get(`http://localhost:8084/api/v1/cart/user/${this.userId}`, {headers: this.requestHeader})
    .subscribe((data: any)=>{
      console.log("get cart data()()()", data);
      this.CARTID = data.id;
      console.log("cart id...", this.CARTID);
      
      this.FOODNAME =data["food"].name;
      this.FOODPRICE = data["amount"];
    })
  }


  async checkout(){
    this.cartService.checkout(this.CARTID).subscribe((data: any)=>{
      console.log(data, "order data");
      if(data.status === 'created'){
        console.log("status created");
        let options = {
          key: 'rzp_test_3NqsJYAcKTqGKa',
          amount: this.FOODPRICE,
          currency: 'INR',
          name: 'Swiggy Payment',
          description: 'Swiggy online payment',
          image: './../../assets/swiggy background.png',
          order_id: data.id,
          handler: function(data: any){
            console.log(data.razorpay_payment_id);
            console.log(data.razorpay_order_id);
            console.log(data.razorpay_signature);
            console.log("payment sucessfull");
            alert("Payment Sucessful!!");
          },
          prefill: {
            name: "",
            email: "",
            contact: ""
            },
            notes: {
              address: "Swiggy Payment department"
              },
              theme: {
              color: "#3399cc"
              }
        }

        let rzp = new Razorpay(options)
        rzp.on('payment.failed', function (data: any){
          console.log(data.error.code);
          console.log(data.error.description);
          console.log(data.error.source);
          console.log(data.error.step);
          console.log(data.error.reason);
          console.log(data.error.metadata.order_id);
          console.log(data.error.metadata.payment_id);
          alert("Oops payment failed!!");
          });
          console.log(rzp, "---");
          
        rzp.open();
        
      }
    })
  }
}
